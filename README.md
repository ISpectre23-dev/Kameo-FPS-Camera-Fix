# Kameo Camera Fix for Xenia Canary

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

1. Close Xenia.
2. Back up any Kameo patch file you already have installed.
3. Copy `4D5307D2 - Kameo Elements of Power.patch.toml` to the Xenia Canary
   `patches` folder, replacing the existing Kameo patch file to avoid duplicate
   entries.
4. Open the patch manager and enable only one of the two framerate options with
   Camera Fix.
5. Make sure patches are enabled in the game configuration.

> **Important:** enable only one Camera Fix option and keep the other one
> disabled. Never enable both simultaneously. Keep `vsync = true` and
> `apply_patches = true`, and use the matching `framerate_limit` value.

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

No external NVIDIA limiter is required. The system must be able to maintain
approximately 100 FPS to achieve the intended behavior.

## Aspect Ratio configuration

The official `Aspect Ratio` patch is independent of the FPS options. It
requires the aspect-ratio configuration specified by Xenia for aspect-ratio
patches; follow the note in the Xenia game-patches README before enabling it.

## Uninstallation

1. Close Xenia.
2. Disable both Camera Fix entries.
3. Remove the installed file or restore your backup of the official Kameo
   patch file.

The patch is applied in memory and does not modify `default.xex`, save data,
profiles, or game content.

## Known limitations

- Only the specified Module Hash is supported.
- Each option is tuned for its specific framerate; this is not a dynamic patch
  and is not designed for uncapped operation.
- Never enable both options at the same time.
- Initial manual validation was stable, but uncommon cameras or situations may
  not have been tested yet.

## Credits

- Original FPS unlock and Aspect Ratio patch: **Margen67**
- Camera fixes and 100 FPS implementation: **ISpectre23**
