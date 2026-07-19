# AGENTS.md

## Propósito

Este repositorio contiene el trabajo para investigar y crear un parche de Xenia Canary que corrija el seguimiento automático de la cámara de **Kameo: Elements of Power** cuando el juego funciona a 60 FPS.

Lee también:

- `PROJECT_BRIEF.md`
- `OFFICIAL_XENIA_REFERENCES.md`

## Rutas autorizadas

### Proyecto

`C:\Users\david\GitHub\KameoCameraFix`

Esta es la raíz del proyecto. Organiza aquí toda la estructura de trabajo.

### Instalación existente de Xenia

`D:\#EMULATORS\XENIA`

Puedes inspeccionar esta ruta y acceder a sus subcarpetas para localizar:

- Xenia Manager.
- Xenia Canary experimental.
- Configuraciones globales y por juego.
- Parches.
- `xenia.log`.
- Partidas y contenido únicamente para lectura y localización.
- Ejecutables y módulos del juego.

No asumas nombres o ubicaciones internas. Localízalos y documéntalos.

## Organización del proyecto

Debes diseñar y crear una estructura clara dentro de la raíz del proyecto.

Como mínimo, separa:

- archivos finales y reutilizables que puedan conservarse en Git;
- documentación final;
- scripts seguros y reutilizables;
- parches de trabajo y parche final;
- copias locales de archivos del juego;
- proyectos de Ghidra;
- tablas o datos de Cheat Engine;
- capturas y resultados de pruebas;
- informes temporales;
- copias de seguridad.

Todo archivo privado, pesado, generado, temporal o procedente del juego debe vivir dentro de una carpeta `_local\`.

Puedes ajustar la estructura si encuentras una organización mejor, pero debes documentarla en el `README.md` que crearás.

## Git

1. Comprueba si la carpeta del proyecto ya es un repositorio Git.
2. Si no lo es, inicialízalo localmente.
3. No crees ningún remoto.
4. No hagas `push`.
5. No crees commits salvo que el usuario lo solicite expresamente.
6. Crea o actualiza `.gitignore`.
7. Usa `.git\info\exclude` solo para exclusiones específicamente locales que no deban formar parte del repositorio.
8. Mantén todos los archivos del juego y datos sensibles dentro de `_local\`.
9. Como mínimo, excluye:

```gitignore
_local/
*.bak
*.tmp
Thumbs.db
.DS_Store
```

10. Antes de terminar cualquier tarea importante, revisa `git status` y confirma que no se rastrean:

- archivos `.xex`;
- partidas;
- perfiles;
- contenido del juego;
- logs de Xenia;
- proyectos o bases de datos de Ghidra;
- tablas de Cheat Engine;
- vídeos o capturas de pruebas;
- copias completas de Xenia;
- archivos generados o temporales.

## Seguridad y límites

- Nunca modifiques permanentemente el `default.xex` original.
- Nunca borres, reemplaces ni sobrescribas partidas, perfiles o contenido del usuario.
- Trabaja con copias de los archivos cuando sea necesario.
- Antes de modificar una configuración o un parche, crea una copia de seguridad local.
- No desactives Windows Defender, SmartScreen, UAC ni otras protecciones del sistema.
- No instales ni desinstales programas sin autorización.
- No subas ni compartas archivos del juego, partidas, perfiles, logs privados ni datos personales.
- No inventes direcciones, opcodes, constantes, hashes o resultados.
- Registra los bytes, instrucciones o valores originales antes de cualquier cambio experimental.
- Prueba una hipótesis cada vez.
- Restaura los valores originales cuando un candidato falle.
- No fuerces la aplicación de un parche cambiando o eliminando un hash.
- Detente únicamente ante permisos del sistema, UAC, riesgo para archivos originales o un bloqueo que no puedas resolver de forma segura.

## Uso de aplicaciones

Tienes autorización para usar computer use sobre:

- Xenia Manager.
- Xenia Canary.
- Cheat Engine.
- Ghidra.
- Explorador de archivos.
- PowerShell o terminal.
- Editores de texto disponibles.

Puedes iniciar, cerrar y configurar estas aplicaciones dentro de los límites indicados.

## Controles de Kameo

Configura tú mismo en Xenia Manager un mapeo de teclado adecuado para controlar Kameo de forma precisa y reproducible.

Debes disponer como mínimo de teclas para:

- stick izquierdo: arriba, abajo, izquierda y derecha;
- stick derecho: arriba, abajo, izquierda y derecha;
- A;
- B;
- Start;
- cualquier otra acción que resulte necesaria para cargar la partida, desplazarte o repetir las pruebas.

Requisitos:

1. Haz una copia de seguridad de la configuración de entrada antes de modificarla.
2. Elige teclas que no entren en conflicto con atajos de Xenia, Windows o las herramientas de análisis.
3. Documenta el mapa definitivo de teclas.
4. Verifica dentro del juego que funcionan.
5. Usa esas teclas para crear pruebas repetibles.
6. Puedes cambiar el mapa más adelante si encuentras una configuración mejor, pero debes registrar el cambio.

## Filosofía del arreglo

Prioriza la solución más pequeña y específica:

1. una constante `float` exclusiva de la cámara;
2. una instrucción PowerPC existente;
3. un pequeño conjunto de instrucciones;
4. un code cave únicamente si no existe una solución más sencilla;
5. un plugin o modificación de Xenia solo como último recurso.

El primer objetivo es un arreglo estable para **60 FPS**. No intentes un arreglo dinámico para cualquier framerate hasta comprender la función y tener un fix de 60 FPS validado.

## Validación

No consideres válido un candidato porque simplemente “mejore” la cámara.

A 60 FPS debe aproximarse claramente al comportamiento original de 30 FPS y no debe provocar:

- vibraciones;
- saltos;
- deriva;
- distancia incorrecta;
- zoom anómalo;
- pérdida de control manual;
- problemas de colisión de cámara;
- crashes;
- regresiones evidentes en otras cámaras o cinemáticas.

## Entregables generados por Codex

Durante el trabajo crea, como mínimo:

- `README.md` con la estructura y uso del proyecto;
- `environment-report.md` tras validar el entorno;
- un registro de hipótesis y candidatos;
- un registro de pruebas;
- el parche final;
- una copia local habilitada para probar;
- `final-report.md` con la explicación técnica y los resultados.

Puedes reorganizar estos documentos dentro de una carpeta `docs\`, siempre que las rutas queden claras en el `README.md`.
