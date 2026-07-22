# Kameo FPS Camera Fix for Xenia Canary

Fixes automatic camera following in **Kameo: Elements of Power** when the game
runs at 60 or 100 FPS. The patch file contains these three options, all
disabled by default:

- `60 FPS + Camera Fix`
- `100 FPS + Camera Fix`
- `Aspect Ratio`

Enable **only one FPS option**. The 60 and 100 FPS options are mutually
exclusive and must never be enabled simultaneously. The official `Aspect
Ratio` patch is independent of the FPS choice.

## Compatibility

- Emulator: Xenia Canary with game patch support.
- Title ID: `4D5307D2`.
- Module Hash: `1A83A0AF5C0EDFE8`.
- Executable: the `default.xex` version that exactly matches this Module Hash.

Other game revisions are not supported by this file.

## Installation

### Manual installation

1. Close Xenia Canary.
2. Back up and remove any existing Kameo patch file from Xenia Canary's
   `patches` folder. Keep only one Kameo patch file installed to avoid duplicate
   entries or conflicting enabled states.
3. Copy `4D5307D2 - Kameo Elements of Power.patch.toml` to Xenia Canary's
   `patches` folder.
4. Open the copied `.patch.toml` file in a text editor and set
   `is_enabled = true` for either `60 FPS + Camera Fix` or
   `100 FPS + Camera Fix`. Keep the other FPS option set to `false`. Never
   enable both simultaneously.
5. In Kameo's configuration, keep `apply_patches = true` and `vsync = true`,
   then set `framerate_limit = 60` or `framerate_limit = 100` to match the
   enabled patch.
6. Launch the game and confirm that `[Patches Applied]` appears in Xenia
   Canary's title bar.

> **Important:** only one FPS option may be enabled at a time. The
> `Aspect Ratio` patch may be enabled independently alongside either FPS option.

### Xenia Manager

In the tested version of Xenia Manager, importing this file as a local patch
may create an empty extensionless file. The patch entries can appear in the
interface without being applied in-game.

To install it through Xenia Manager:

1. Download Kameo's official patch through Xenia Manager.
2. Locate the generated `4D5307D2 - Kameo.patch.toml` file in Xenia Canary's
   `patches` folder.
3. Replace the contents of that file with the contents of this repository's
   patch file. Keep the filename exactly as `4D5307D2 - Kameo.patch.toml` when
   using Xenia Manager, as this is the filename generated and managed by it for
   Kameo.
4. Enable either `60 FPS + Camera Fix` or `100 FPS + Camera Fix`, but never
   enable both options at the same time.

## 60 FPS configuration

Enable only `60 FPS + Camera Fix`, disable `100 FPS + Camera Fix`, and use:

```toml
vsync = true
framerate_limit = 60
apply_patches = true
```

Never enable the 100 FPS option at the same time.

## 100 FPS configuration

Enable only `100 FPS + Camera Fix`, disable `60 FPS + Camera Fix`, and use:

```toml
vsync = true
framerate_limit = 100
apply_patches = true
```

Never enable the 60 FPS option at the same time.

No external limiter is required. The system must be able to maintain
approximately 100 FPS to achieve the intended behavior.

## Aspect Ratio configuration

The official `Aspect Ratio` patch is independent of the FPS options. It
requires the aspect-ratio configuration specified by Xenia for aspect-ratio
patches; follow the note in the Xenia game-patches README before enabling it.

## Uninstallation

1. Close Xenia Canary.
2. Disable both Camera Fix entries.
3. Remove the installed file or restore your backup of the official Kameo
   patch file.

The patch is applied in memory and does not modify `default.xex`, save data,
profiles, or game content.

## Known limitations

- Only the specified Module Hash is supported.
- Each option is tuned for its specific framerate; this is not a dynamic patch
  and is not designed for uncapped operation.
- Never enable both FPS options at the same time.
- Both options were tested in regular gameplay, but uncommon scripted camera
  sequences or untested areas may still behave differently.

## Credits

- Original FPS unlock and Aspect Ratio patch: **Margen67**
- Camera fixes and 100 FPS implementation: **ISpectre23**
