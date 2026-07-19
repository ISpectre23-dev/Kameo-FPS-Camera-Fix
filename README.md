# KameoCameraFix

Proyecto de investigación para crear un parche de Xenia Canary que corrija el
seguimiento automático de la cámara de **Kameo: Elements of Power** a 60 FPS.

La fase inicial solo prepara y valida el entorno. No contiene todavía un
candidato de parche ni resultados de ingeniería inversa.

## Estructura

| Ruta | Contenido |
| --- | --- |
| `docs/` | Informes reproducibles, hipótesis y registro de pruebas. |
| `patches/final/` | Parche final deshabilitado por defecto, cuando esté validado. |
| `patches/work/` | Candidatos de parche aptos para conservar en Git y sin datos privados. |
| `scripts/` | Automatización segura y reutilizable. |
| `_local/game-files/` | Copias de módulos del juego; nunca originales. |
| `_local/ghidra/projects/` | Proyectos y bases de datos de Ghidra. |
| `_local/ghidra/exports/` | Exportaciones generadas por Ghidra. |
| `_local/cheat-engine/` | Tablas y datos de Cheat Engine. |
| `_local/captures/` | Capturas y vídeos de pruebas. |
| `_local/test-results/` | Resultados generados de pruebas. |
| `_local/reports/` | Informes temporales o privados. |
| `_local/logs/` | Copias locales de logs. |
| `_local/backups/` | Copias de seguridad previas a cambios de configuración. |
| `_local/temp/` | Archivos temporales. |
| `_local/xenia/patches-enabled/` | Copia local habilitada de un parche durante las pruebas. |
| `ghidra_12.0.4_PUBLIC/` | Dependencia local de Ghidra, excluida de Git. |

Todo archivo privado, pesado, generado, temporal o procedente del juego debe
permanecer dentro de `_local/`. La instalación original de Xenia y los archivos
del juego en `D:\#EMULATORS\XENIA` se consideran fuentes de solo lectura, salvo
cambios de configuración reversibles precedidos por una copia de seguridad.

## Documentación

- [Resumen y criterios del proyecto](PROJECT_BRIEF.md)
- [Referencias oficiales](OFFICIAL_XENIA_REFERENCES.md)
- [Validación del entorno](docs/environment-report.md)
- [Hipótesis y candidatos](docs/hypothesis-log.md)
- [Registro de pruebas](docs/test-log.md)

## Flujo de trabajo

1. Consultar la conclusión de `docs/environment-report.md` y resolver cualquier
   bloqueo antes de iniciar la investigación.
2. Registrar cada hipótesis antes de probarla.
3. Conservar bytes, instrucciones y valores originales antes de cualquier cambio.
4. Probar un solo candidato a la vez con una referencia reproducible a 30 FPS.
5. Guardar artefactos privados y resultados generados únicamente en `_local/`.
6. Publicar en `patches/final/` solo un parche validado, ligado al hash exacto y
   con `is_enabled = false`.

No se crean commits ni se hace `push` salvo solicitud expresa.
