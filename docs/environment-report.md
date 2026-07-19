# Informe de validación del entorno

Fecha de validación: 2026-07-19  
Proyecto: `C:\Users\david\GitHub\KameoCameraFix`

## Conclusión

`NOT READY`

La preparación del repositorio, Xenia, Java, Ghidra y la importación XEX están
operativas. La fase de ingeniería inversa no debe empezar todavía porque quedan
dos comprobaciones prácticas sin validar:

1. Cheat Engine 7.7 inicia el proceso, pero no crea una ventana utilizable. Por
   ello no se pudieron comprobar los tipos big-endian, `MEM_MAPPED` ni el
   adjunto de solo lectura a `xenia_canary.exe`.
2. Xenia acepta y registra el backend WinKey y el mapa de teclado, pero las
   pulsaciones enviadas mediante computer use no fueron detectadas por Kameo.
   No se pudo confirmar movimiento, cámara ni navegación por menús.

No se instaló ni desinstaló software, no se cambió ninguna protección de
Windows y no se inició la investigación de la cámara.

## Estructura creada

| Ruta | Uso |
| --- | --- |
| `docs/` | Informes finales, hipótesis y pruebas reproducibles. |
| `patches/work/` | Candidatos conservables, todavía inexistentes. |
| `patches/final/` | Parche final, todavía inexistente. |
| `scripts/` | Herramientas seguras y reutilizables. |
| `_local/game-files/` | Copias de módulos del juego. |
| `_local/ghidra/projects/` | Proyectos y bases de datos de Ghidra. |
| `_local/ghidra/exports/` | Exportaciones de Ghidra. |
| `_local/cheat-engine/` | Tablas y datos de Cheat Engine. |
| `_local/captures/` | Capturas y vídeos de pruebas. |
| `_local/test-results/` | Resultados generados. |
| `_local/reports/` | Informes temporales y logs privados de herramientas. |
| `_local/logs/` | Copias de logs de Xenia. |
| `_local/backups/` | Copias previas a cambios de configuración. |
| `_local/temp/` | Compilaciones y otros temporales. |
| `_local/xenia/patches-enabled/` | Copia del parche habilitado durante la prueba. |
| `ghidra_12.0.4_PUBLIC/` | Dependencia local, excluida de Git. |

## Git y exclusiones

- Ya existía un repositorio Git en la rama `main`.
- Ya existía el remoto `origin`; no se creó, modificó ni utilizó.
- No se crearon commits ni se hizo `push`.
- `.gitignore` excluye `_local/`, `ghidra_*_PUBLIC/`, proyectos de Ghidra,
  módulos e imágenes de juego, logs de Xenia, tablas de Cheat Engine,
  capturas, vídeos, copias de seguridad y temporales.
- `.git/info/exclude` se conservó sin cambios: no se detectó una exclusión
  exclusivamente local que no estuviera ya cubierta por `.gitignore`.
- La comprobación final con `git status --ignored` confirma expresamente
  `!! ghidra_12.0.4_PUBLIC/` y `!! _local/`.

## Aplicaciones y componentes

| Componente | Ruta o identificación | Versión | Resultado |
| --- | --- | --- | --- |
| ChatGPT/Codex para Windows | Paquete `OpenAI.Codex_26.715.4045.0_x64` | 26.715.4045.0 | Operativo; computer use controló las aplicaciones visibles. |
| Xenia Manager | `D:\#EMULATORS\XENIA\XeniaManager.exe` | Archivo 4.2.2; producto `2026-06-27-f209ab1` | Abre, edita y guarda la configuración de Kameo. |
| Xenia Canary | `D:\#EMULATORS\XENIA\Emulators\Xenia Canary\xenia_canary.exe` | `canary_experimental@a635ac64f`, 2026-07-19 | Abre Kameo y aplica el parche. |
| Cheat Engine | `C:\Program Files\Cheat Engine\cheatengine-x86_64.exe` | 7.7.0.10621; producto 7.7 | El proceso arranca, pero no aparece una ventana. |
| Ghidra | `ghidra_12.0.4_PUBLIC\ghidraRun.bat` | 12.0.4 | La interfaz abre sin error y el análisis headless termina. |
| JDK | `C:\Program Files\Microsoft\jdk-21.0.11.10-hotspot` | Microsoft OpenJDK 21.0.11 LTS, 64 bits; `javac` 21.0.11 | Operativo desde terminal. |
| XEXLoaderWV | Perfil de Ghidra en `%APPDATA%\ghidra\ghidra_12.0.4_PUBLIC\Extensions\XEXLoaderWV` | 13.0.0 | Instalado, habilitado y utilizado durante la importación. |

Ghidra GUI conservaba un JDK 25 instalado como runtime guardado en su perfil,
pero el JDK 21 de 64 bits exigido está instalado y funciona. La configuración
temporal de lanzamiento usada durante el diagnóstico se restauró; el
`launch.properties` actual coincide por SHA-256 con su copia previa.

## Kameo y Xenia

### Localización

- Imagen usada por Xenia:
  `D:\#EMULATORS\XENIA\Games\Kameo - Elements of Power (Europe) (En,Ja,Fr,De,Es,It,Zh,Ko) (Rev 1).iso`
- Configuración por juego:
  `D:\#EMULATORS\XENIA\Emulators\Xenia Canary\config\Kameo.config.toml`
- Configuración general:
  `D:\#EMULATORS\XENIA\Emulators\Xenia Canary\xenia-canary.config.toml`
- Parche:
  `D:\#EMULATORS\XENIA\Emulators\Xenia Canary\patches\4D5307D2 - Kameo.patch.toml`
- Log:
  `D:\#EMULATORS\XENIA\Emulators\Xenia Canary\xenia.log`

El parche existente de 60 FPS se copió sin modificaciones a
`_local/xenia/patches-enabled/`. No se creó ni investigó ningún arreglo de
cámara.

### Metadatos observados

| Dato | Valor |
| --- | --- |
| Módulo | `\Device\Cdrom0\default.xex` |
| Title ID | `4D5307D2` |
| Media ID | `45BB5521` |
| Module Hash | `1A83A0AF5C0EDFE8` |
| Versión mostrada por Xenia | `v0.0.0.5` |
| Imports de sistema del XEX | `2.0.2099.0`, mínimo `2.0.1861.0` |
| Build de Xenia | `canary_experimental@a635ac64f on Jul 19 2026` |
| Parche | `Kameo: Elements of Power (4D5307D2) - 60 FPS`, aplicado |
| Title Update | No observado; el log no registra carga o aplicación de un TU. |

`apply_patches = true`, `hid = "winkey"` y `keyboard_mode = 1` quedaron
confirmados en el log de la ejecución. La copia privada del log está en
`_local/logs/xenia-environment-validation.log`.

## Copia de `default.xex` e importación

El módulo se extrajo directamente de la imagen en modo de solo lectura mediante
`scripts/ExportXdvdfsFile.java`; no se modificó el original.

| Dato | Valor |
| --- | --- |
| Copia | `_local/game-files/default.xex` |
| Tamaño | 3.682.304 bytes |
| Partición XDVDFS | `0x0FD90000` |
| Offset de origen | `0x0E964A000` |
| SHA-256 de la copia | `124D812F75F465D393134A84792412BF01A135EA1D6EA4EAFEF82AA4A5F739C4` |
| Proyecto | `_local/ghidra/projects/KameoEnvironmentValidation.gpr` |
| Loader | `XEX Loader by Warranty Voider` |
| Lenguaje | `PowerPC:BE:64:A2ALT-32addr:default` |
| Resultado | Importación, análisis y guardado completados. |

Durante el análisis aparecieron avisos de datos PNG no válidos y un mensaje
`out of input bytes`, pero Ghidra informó explícitamente `Analysis succeeded`,
`Save succeeded` e `Import succeeded`. Los logs completos permanecen dentro de
`_local/reports/`.

## Entrada de Kameo

Antes de cambiar la configuración se guardaron copias de Xenia Manager, la
configuración general, la configuración de Kameo, `xconfig.settings` y el
parche en:

`_local/backups/xenia-input/2026-07-19-before-keyboard-setup/`

Mapa definitivo guardado:

| Control Xbox 360 | Tecla |
| --- | --- |
| Stick izquierdo arriba / abajo / izquierda / derecha | `W` / `S` / `A` / `D` |
| Stick derecho arriba / abajo / izquierda / derecha | Flechas `↑` / `↓` / `←` / `→` |
| A | `;` |
| B | `'` |
| Start | `X` |
| Back | `Z` |
| X / Y | `L` / `P` |
| Gatillo izquierdo | `Q` o `I` |
| Gatillo derecho | `E` o `O` |
| LB / RB | `1` / `3` |
| Pulsar stick izquierdo / derecho | `F` / `K` |
| Cruceta | `Ctrl+W` / `Ctrl+S` / `Ctrl+A` / `Ctrl+D` |

Estas teclas evitan los atajos principales de Xenia y reservan las flechas para
la cámara. Xenia creó un controlador de teclado en el puerto 0, pero la prueba
práctica mediante computer use no atravesó de forma fiable `PULSA START` ni
interrumpió la demo. Por tanto, el mapa está configurado pero no validado dentro
del juego.

## Resultado de las pruebas

| Prueba | Resultado |
| --- | --- |
| Abrir y controlar Xenia Manager | Correcto |
| Guardar configuración por juego | Correcto |
| Abrir Xenia Canary y Kameo | Correcto |
| Confirmar Canary experimental actual | Correcto |
| Confirmar `apply_patches` y parche aplicado | Correcto |
| Abrir Ghidra mediante `ghidraRun.bat` | Correcto |
| Ver XEXLoaderWV habilitado | Correcto |
| Importar XEX como PowerPC big-endian | Correcto |
| Ejecutar JDK 21 x64 y `javac` | Correcto |
| Abrir Cheat Engine | Fallido: proceso sin ventana |
| Tipos big-endian en Cheat Engine | No verificable |
| Activar `MEM_MAPPED` | No verificable |
| Adjuntar Cheat Engine a Xenia sin escribir memoria | No verificable |
| Acceder a menús con teclado | No confirmado |
| Mover personaje y cámara con teclado | No confirmado |
| Controlar terminal y archivos | Correcto |

## Problemas y próximos pasos permitidos

1. Resolver por qué las variantes estándar y AVX2 de Cheat Engine 7.7 quedan
   como procesos sin ventana. No se deben instalar drivers, reinstalar la
   aplicación ni cambiar Defender, SmartScreen o UAC sin autorización expresa.
2. Validar las teclas con una pulsación física del usuario o con un mecanismo de
   computer use que mantenga los eventos el tiempo suficiente para el sondeo
   WinKey de Xenia. Si responde, probar Start, movimiento, cámara y A/B en una
   zona segura.
3. Repetir las tres comprobaciones de Cheat Engine con Xenia abierto y sin
   realizar ninguna escritura de memoria.
4. Cambiar la conclusión a `READY` solo cuando ambos bloqueos queden resueltos.

