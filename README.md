# Kameo FPS Camera Fix for Xenia Canary

Fixes automatic camera following in **Kameo: Elements of Power** at 60 and 100
FPS for the supported base-game revision and Title Update 2. Two patch files are
available:

- Base game: `4D5307D2 - Kameo Elements of Power.patch.toml`
- Title Update 2: `4D5307D2 - Kameo Elements of Power (TU2).patch.toml`

Each file contains these three options, all disabled by default:

- `60 FPS + Camera Fix`
- `100 FPS + Camera Fix`
- `Aspect Ratio`

Enable **only one FPS option**. The 60 and 100 FPS options are mutually
exclusive and must never be enabled simultaneously. The official `Aspect
Ratio` patch is independent of the FPS choice.

## Compatibility

| Game revision | Patch file | Module Hash | Tested Media ID |
|---|---|---|---|
| Base game | `4D5307D2 - Kameo Elements of Power.patch.toml` | `1A83A0AF5C0EDFE8` | `45BB5521` |
| Title Update 2 | `4D5307D2 - Kameo Elements of Power (TU2).patch.toml` | `B94D3AB68548DF97` | `45BB5521` |

Use only the patch file matching the active Module Hash. Title Update 2 must be
installed and active to use the TU2 file. Only the two Module Hashes listed
above are supported. Other disc revisions may also be compatible if they
produce one of those exact hashes; any other Module Hash is unsupported.

The listed Media ID identifies the tested edition. You can find the active
Module Hash in `xenia.log` after launching the game.

## Installation

### Step 1: identify the correct patch file

Do this before copying or editing any patch file:

1. Disable or remove any currently installed Kameo patch.
2. Launch the game revision you want to use once, then close Xenia Canary.
3. Open the latest `xenia.log` and search for `Module Hash:`.
4. Use the exact match below:

   | Module Hash | File to use |
   |---|---|
   | `1A83A0AF5C0EDFE8` | `4D5307D2 - Kameo Elements of Power.patch.toml` |
   | `B94D3AB68548DF97` | `4D5307D2 - Kameo Elements of Power (TU2).patch.toml` |

5. If your Module Hash does not match either value exactly, stop. Neither patch
   file is confirmed to support that game revision.

> **TU2 users:** the TU2 file works only while Title Update 2 is installed and
> active.

### Step 2: choose one installation method

Use either **Manual installation** or **Xenia Manager**. Do not install the
patch through both methods at the same time.

### Manual installation

1. Close Xenia Canary.
2. Open Xenia Canary's `patches` folder.
3. Back up and remove every existing Kameo patch file from that folder. After
   installation, only one Kameo patch file should be present.
4. Copy the file selected in Step 1 into the `patches` folder.
5. Open the copied `.patch.toml` file in a text editor.
6. Choose one FPS option:
   - For 60 FPS, set `is_enabled = true` under `60 FPS + Camera Fix` and keep
     `100 FPS + Camera Fix` set to `false`.
   - For 100 FPS, set `is_enabled = true` under `100 FPS + Camera Fix` and keep
     `60 FPS + Camera Fix` set to `false`.
7. Optionally enable `Aspect Ratio`. It is independent and may be used with
   either FPS option.
8. In Kameo's Xenia configuration, use the settings matching your selected
   option:

   **60 FPS**
   ```toml
   vsync = true
   framerate_limit = 60
   apply_patches = true
   ```

   **100 FPS**
   ```toml
   vsync = true
   framerate_limit = 100
   apply_patches = true
   ```

9. Launch the game.
10. Confirm that `[Patches Applied]` appears in Xenia Canary's title bar.

If `[Patches Applied]` does not appear, close Xenia and check all four items:

- the installed patch file matches the active Module Hash;
- only one Kameo patch file is present;
- exactly one FPS option has `is_enabled = true`;
- `apply_patches = true` is set in Kameo's Xenia configuration.

### Xenia Manager

Do **not** import either repository file with Xenia Manager's local-patch
importer. In the tested version, `Xenia Manager v4.2.2`, this may create an
empty extensionless file while still showing patch entries in the interface.

Use this workaround instead:

1. Close Xenia Canary.
2. In Xenia Manager, download Kameo's official patch.
3. Locate the generated file in Xenia Canary's `patches` folder:

   `4D5307D2 - Kameo.patch.toml`

4. Open both of these files in a text editor:
   - the repository file selected in Step 1;
   - the generated `4D5307D2 - Kameo.patch.toml` file.
5. Copy the entire contents of the repository file.
6. Replace the entire contents of the generated file and save it.
7. Keep its filename exactly as:

   `4D5307D2 - Kameo.patch.toml`

8. In Xenia Manager, enable either `60 FPS + Camera Fix` or
   `100 FPS + Camera Fix`. Never enable both.
9. Set Kameo's Xenia configuration to match the selected FPS option, using the
    configuration values shown below.
10. Launch the game and confirm that `[Patches Applied]` appears in Xenia
    Canary's title bar.

Do not combine the contents of the base-game and TU2 files. Use only the file
matching the active Module Hash.

## 60 FPS configuration

This configuration is shared by the base-game and TU2 patch files. Enable only
`60 FPS + Camera Fix`, disable `100 FPS + Camera Fix`, and use:

```toml
vsync = true
framerate_limit = 60
apply_patches = true
```

## 100 FPS configuration

This configuration is shared by the base-game and TU2 patch files. Enable only
`100 FPS + Camera Fix`, disable `60 FPS + Camera Fix`, and use:

```toml
vsync = true
framerate_limit = 100
apply_patches = true
```

No external limiter is required. The system must be able to maintain
approximately 100 FPS to achieve the intended behavior.

## Aspect Ratio configuration

The official `Aspect Ratio` patch is independent of the FPS options. It
requires the aspect-ratio configuration specified by Xenia for aspect-ratio
patches; follow the note in the Xenia game-patches README before enabling it.

## Uninstallation

1. Close Xenia Canary.
2. Disable both Camera Fix entries.
3. Remove the patch file corresponding to the revision you used, or restore
   your backup of the official Kameo patch file.

The patches are applied in memory and do not modify `default.xex`, save data,
profiles, or game content.

## Known limitations

- Only Module Hashes `1A83A0AF5C0EDFE8` and `B94D3AB68548DF97` are supported.
- Each option is tuned for its specific framerate; uncapped operation is not
  supported.
- Never enable the 60 and 100 FPS options simultaneously.
- Both FPS options passed initial manual validation on the supported base-game
  revision and Title Update 2.
- Uncommon scripted cameras, areas, or situations may not have been tested yet.

## Credits

- Base-game FPS unlock and Aspect Ratio patch: **Margen67**
- TU2 FPS unlock and Aspect Ratio patch: **Margen67, ICUP321**
- Camera fixes and 100 FPS implementations for the base game and TU2:
  **ISpectre23**
