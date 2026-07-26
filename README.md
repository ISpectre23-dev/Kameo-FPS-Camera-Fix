# Kameo FPS Camera Fix for Xenia Canary

Fixes automatic camera following in **Kameo: Elements of Power** at higher,
variable and uncapped framerates for the supported base-game revision and Title
Update 2.

Two patch files are available:

- Base game: `4D5307D2 - Kameo Elements of Power.patch.toml`
- Title Update 2: `4D5307D2 - Kameo Elements of Power (TU2).patch.toml`

Each file contains these three options, all disabled by default:

- `60 FPS`
- `FPS Camera Fix`
- `Aspect Ratio`

The `60 FPS` and `FPS Camera Fix` patches are independent:

- `60 FPS` changes the game's presentation interval and is used for 60 FPS or
  higher fixed framerates with Xenia VSync enabled.
- `FPS Camera Fix` corrects automatic camera following at framerates above
  30 FPS, including fixed, variable and uncapped operation.
- `Aspect Ratio` is independent of both FPS-related patches.

## Compatibility

| Game revision | Patch file | Module Hash | Tested Media ID |
|---|---|---|---|
| Base game | `4D5307D2 - Kameo Elements of Power.patch.toml` | `1A83A0AF5C0EDFE8` | `45BB5521` |
| Title Update 2 | `4D5307D2 - Kameo Elements of Power (TU2).patch.toml` | `B94D3AB68548DF97` | `45BB5521` |

Use only the patch file matching the active Module Hash. Title Update 2 must be
installed and active to use the TU2 file. Only the two Module Hashes listed
above are supported.

Other disc revisions may also be compatible if they produce one of those exact
hashes; any other Module Hash is unsupported.

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
6. Enable the patches required by your intended configuration:

   - **Original 30 FPS:** no FPS-related patch is required.
   - **60 FPS:** enable `60 FPS` and `FPS Camera Fix`.
   - **Higher fixed framerate with VSync enabled:** enable `60 FPS` and
     `FPS Camera Fix`, then set the desired `framerate_limit`.
   - **Uncapped or variable framerate with VSync disabled:** enable only
     `FPS Camera Fix`.

7. Optionally enable `Aspect Ratio`. It is independent of both FPS-related
   patches.
8. Set `apply_patches = true` in Kameo's Xenia configuration.
9. Launch the game.
10. Confirm that `[Patches Applied]` appears in Xenia Canary's title bar.

If `[Patches Applied]` does not appear, close Xenia and check:

- the installed patch file matches the active Module Hash;
- only one Kameo patch file is present;
- the intended patch entries have `is_enabled = true`;
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

8. In Xenia Manager, enable the patches required by your intended configuration.
9. Set Kameo's Xenia configuration using one of the examples below.
10. Launch the game and confirm that `[Patches Applied]` appears in Xenia
    Canary's title bar.

Do not combine the contents of the base-game and TU2 files. Use only the file
matching the active Module Hash.

## Configuration examples

### Original 30 FPS

No FPS-related patch is required.

```toml
vsync = true
framerate_limit = 0
apply_patches = true
```

`FPS Camera Fix` may remain disabled at the original 30 FPS.

### 60 FPS

Enable:

- `60 FPS`
- `FPS Camera Fix`

Use:

```toml
vsync = true
framerate_limit = 0
apply_patches = true
```

With this configuration, the `60 FPS` patch runs the game at 60 FPS.

### Higher fixed framerate

Enable:

- `60 FPS`
- `FPS Camera Fix`

Set the desired limit, for example:

```toml
vsync = true
framerate_limit = 100
apply_patches = true
```

The same structure may be used for other supported limits. Actual framerate
accuracy depends on Xenia Canary's limiter, the selected settings and system
performance.

### Uncapped or variable framerate

Enable only:

- `FPS Camera Fix`

Use:

```toml
vsync = false
framerate_limit = 0
apply_patches = true
```

In this configuration, the `60 FPS` patch is unnecessary because disabling
Xenia VSync already allows the game to run uncapped.

An external limiter may still be used if desired.

## Aspect Ratio configuration

The official `Aspect Ratio` patch is independent of both FPS-related patches.
It requires the aspect-ratio configuration specified by Xenia for aspect-ratio
patches; follow the note in the Xenia game-patches README before enabling it.

## Uninstallation

1. Close Xenia Canary.
2. Disable `60 FPS`, `FPS Camera Fix` and `Aspect Ratio`.
3. Remove the patch file corresponding to the revision you used, or restore
   your backup of the official Kameo patch file.

The patches are applied in memory and do not modify `default.xex`, save data,
profiles or game content.

## Known limitations

- Only Module Hashes `1A83A0AF5C0EDFE8` and `B94D3AB68548DF97` are supported.
- A complete playthrough has not yet been performed.
- Uncommon scripted cameras, areas or situations may not have been tested yet.

## Credits

- Base-game FPS unlock and Aspect Ratio patch: **Margen67**
- TU2 FPS unlock and Aspect Ratio patch: **Margen67, ICUP321**
- Base-game and TU2 FPS Camera Fix: **ISpectre23**
