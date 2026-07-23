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

### Manual installation

1. Launch the intended game revision once with any Kameo patches disabled,
   then close Xenia Canary.
2. Check the active Module Hash in the latest `xenia.log`.
3. Select the matching base-game or TU2 patch file from the compatibility table.
4. Back up and remove any other Kameo patch files from Xenia Canary's `patches`
   folder to prevent duplicate entries or conflicting enabled states.
5. Copy only the matching patch file to the `patches` folder.
6. Open the copied `.patch.toml` file in a text editor.
7. Set `is_enabled = true` for either `60 FPS + Camera Fix` or
   `100 FPS + Camera Fix`.
8. Keep the other FPS option set to `false`. Never enable both simultaneously.
9. In Kameo's configuration, keep `apply_patches = true` and `vsync = true`,
   then set `framerate_limit = 60` or `framerate_limit = 100` to match the
   enabled option.
10. Launch the game and confirm that `[Patches Applied]` appears in Xenia
    Canary's title bar.

> **Important:** only one FPS option may be enabled at a time. The
> `Aspect Ratio` patch is independent and may be enabled alongside either FPS
> option.

### Xenia Manager

In the tested version of `Xenia Manager v4.2.2`, importing either repository
file as a local patch may create an empty extensionless file. The patch entries
can then appear in the interface without being applied in-game.

To install the appropriate revision through Xenia Manager:

1. Download Kameo's official patch through Xenia Manager.
2. Locate the generated `4D5307D2 - Kameo.patch.toml` file in Xenia Canary's
   `patches` folder.
3. If the active Module Hash is not already known, launch the intended revision
   once and check the latest `xenia.log`.
4. Replace only the contents of the generated file with the contents of the
   matching repository file:
   - use `4D5307D2 - Kameo Elements of Power.patch.toml` for
     `1A83A0AF5C0EDFE8`;
   - use `4D5307D2 - Kameo Elements of Power (TU2).patch.toml` for
     `B94D3AB68548DF97`.
5. Keep the generated filename exactly as `4D5307D2 - Kameo.patch.toml`.
6. Enable either `60 FPS + Camera Fix` or `100 FPS + Camera Fix`, but never
   enable both options at the same time.

Do not combine the contents of both repository files or keep both revisions
installed simultaneously through Xenia Manager.

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
