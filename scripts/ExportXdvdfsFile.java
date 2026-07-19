import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.HexFormat;
import java.util.Set;

/**
 * Exports one root file from an Xbox 360 XDVDFS disc image.
 *
 * <p>The parser follows Xenia's read-only DiscImageDevice layout: it probes the
 * same game-partition offsets, verifies MICROSOFT*XBOX*MEDIA in sector 32, and
 * walks the root directory's binary tree. The source image is never writable.
 */
public final class ExportXdvdfsFile {
  private static final long SECTOR_SIZE = 2048;
  private static final long[] GAME_OFFSETS = {
      0x00000000L,
      0x0000FB20L,
      0x00020600L,
      0x02080000L,
      0x0FD90000L
  };
  private static final byte[] MAGIC =
      "MICROSOFT*XBOX*MEDIA".getBytes(StandardCharsets.US_ASCII);

  private ExportXdvdfsFile() {}

  private record Entry(String name, long offset, long length) {}

  public static void main(String[] args) throws Exception {
    if (args.length != 3) {
      System.err.println(
          "Usage: ExportXdvdfsFile <image.iso> <root-file-name> <destination>");
      System.exit(2);
    }

    Path imagePath = Path.of(args[0]).toAbsolutePath().normalize();
    String requestedName = args[1];
    Path destination = Path.of(args[2]).toAbsolutePath().normalize();

    if (!Files.isRegularFile(imagePath)) {
      throw new IOException("Image does not exist: " + imagePath);
    }
    if (Files.exists(destination)) {
      throw new IOException("Destination already exists: " + destination);
    }
    Files.createDirectories(destination.getParent());

    long gameOffset;
    Entry entry;
    try (FileChannel image =
        FileChannel.open(imagePath, StandardOpenOption.READ)) {
      gameOffset = findGameOffset(image);
      entry = findRootEntry(image, gameOffset, requestedName);
      copyEntry(image, entry, destination);
    }

    System.out.println("source_image=" + imagePath);
    System.out.printf("game_offset=0x%x%n", gameOffset);
    System.out.println("file_name=" + entry.name());
    System.out.printf("source_offset=0x%x%n", entry.offset());
    System.out.println("length=" + entry.length());
    System.out.println("destination=" + destination);
    System.out.println("sha256=" + sha256(destination));
  }

  private static long findGameOffset(FileChannel image) throws IOException {
    ByteBuffer header = ByteBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN);
    for (long candidate : GAME_OFFSETS) {
      long headerOffset = candidate + (32 * SECTOR_SIZE);
      if (headerOffset + header.capacity() > image.size()) {
        continue;
      }
      readExactly(image, headerOffset, header);
      boolean matches = true;
      for (int index = 0; index < MAGIC.length; index++) {
        if (header.get(index) != MAGIC[index]) {
          matches = false;
          break;
        }
      }
      if (matches) {
        return candidate;
      }
    }
    throw new IOException("No XDVDFS game partition was found.");
  }

  private static Entry findRootEntry(
      FileChannel image, long gameOffset, String requestedName)
      throws IOException {
    ByteBuffer header = ByteBuffer.allocate(32).order(ByteOrder.LITTLE_ENDIAN);
    readExactly(image, gameOffset + (32 * SECTOR_SIZE), header);

    long rootSector = Integer.toUnsignedLong(header.getInt(20));
    long rootSize = Integer.toUnsignedLong(header.getInt(24));
    if (rootSize < 13 || rootSize > 32L * 1024 * 1024) {
      throw new IOException("Invalid XDVDFS root directory size: " + rootSize);
    }

    long rootOffset = gameOffset + (rootSector * SECTOR_SIZE);
    if (rootOffset < 0 || rootOffset + rootSize > image.size()) {
      throw new IOException("The XDVDFS root directory is outside the image.");
    }

    ByteBuffer root =
        ByteBuffer.allocate(Math.toIntExact(rootSize)).order(ByteOrder.LITTLE_ENDIAN);
    readExactly(image, rootOffset, root);

    ArrayDeque<Integer> pending = new ArrayDeque<>();
    Set<Integer> visited = new HashSet<>();
    pending.push(0);

    while (!pending.isEmpty()) {
      int ordinal = pending.pop();
      if (!visited.add(ordinal)) {
        throw new IOException(
            "Cycle detected in XDVDFS directory tree at ordinal " + ordinal);
      }

      int entryOffset = Math.multiplyExact(ordinal, 4);
      if (entryOffset < 0 || entryOffset + 14 > root.limit()) {
        throw new IOException(
            "Invalid XDVDFS directory entry at ordinal " + ordinal);
      }

      int nodeLeft = Short.toUnsignedInt(root.getShort(entryOffset));
      int nodeRight = Short.toUnsignedInt(root.getShort(entryOffset + 2));
      long sector = Integer.toUnsignedLong(root.getInt(entryOffset + 4));
      long length = Integer.toUnsignedLong(root.getInt(entryOffset + 8));
      int attributes = Byte.toUnsignedInt(root.get(entryOffset + 12));
      int nameLength = Byte.toUnsignedInt(root.get(entryOffset + 13));

      if (entryOffset + 14 + nameLength > root.limit()) {
        throw new IOException(
            "Invalid XDVDFS name length at ordinal " + ordinal);
      }

      byte[] nameBytes = new byte[nameLength];
      ByteBuffer nameView = root.duplicate();
      nameView.position(entryOffset + 14);
      nameView.get(nameBytes);
      String name = new String(nameBytes, StandardCharsets.US_ASCII);

      if (name.equalsIgnoreCase(requestedName)) {
        if ((attributes & 0x10) != 0) {
          throw new IOException(
              "Requested root entry is a directory: " + requestedName);
        }
        long fileOffset = gameOffset + (sector * SECTOR_SIZE);
        if (fileOffset < 0 || fileOffset + length > image.size()) {
          throw new IOException("Requested file is outside the image.");
        }
        return new Entry(name, fileOffset, length);
      }

      if (nodeRight != 0) {
        pending.push(nodeRight);
      }
      if (nodeLeft != 0) {
        pending.push(nodeLeft);
      }
    }

    throw new IOException(
        "Root file was not found in the image: " + requestedName);
  }

  private static void copyEntry(
      FileChannel image, Entry entry, Path destination) throws IOException {
    try (FileChannel output =
        FileChannel.open(
            destination,
            StandardOpenOption.CREATE_NEW,
            StandardOpenOption.WRITE)) {
      long position = entry.offset();
      long remaining = entry.length();
      while (remaining > 0) {
        long copied = image.transferTo(position, remaining, output);
        if (copied <= 0) {
          throw new IOException("Unexpected end of image while copying.");
        }
        position += copied;
        remaining -= copied;
      }
    } catch (Exception error) {
      Files.deleteIfExists(destination);
      throw error;
    }
  }

  private static void readExactly(
      FileChannel channel, long position, ByteBuffer buffer) throws IOException {
    buffer.clear();
    while (buffer.hasRemaining()) {
      int count = channel.read(buffer, position + buffer.position());
      if (count < 0) {
        throw new IOException("Unexpected end of image.");
      }
    }
    buffer.flip();
  }

  private static String sha256(Path path)
      throws IOException, NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    try (var input = Files.newInputStream(path)) {
      byte[] buffer = new byte[1024 * 1024];
      int count;
      while ((count = input.read(buffer)) >= 0) {
        if (count > 0) {
          digest.update(buffer, 0, count);
        }
      }
    }
    return HexFormat.of().withUpperCase().formatHex(digest.digest());
  }
}
