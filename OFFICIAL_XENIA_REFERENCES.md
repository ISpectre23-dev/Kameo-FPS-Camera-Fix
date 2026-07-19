# OFFICIAL_XENIA_REFERENCES.md

## Regla principal

Para crear, probar y empaquetar el parche, prioriza documentación y repositorios oficiales o mantenidos directamente por los proyectos implicados.

No dependas de tutoriales antiguos, resultados de buscadores sin verificar o snippets aislados cuando exista documentación oficial.

## Guía oficial de parches de Xenia Canary

Repositorio:

https://github.com/xenia-canary/game-patches

Guía principal de instalación, uso y creación de parches:

https://github.com/xenia-canary/game-patches/blob/main/.github/README.md

La guía contiene, entre otras cosas:

- requisitos de Xenia Canary experimental;
- instalación de parches;
- identificación del Module Hash mediante `xenia.log`;
- configuración de Cheat Engine;
- tipos big-endian;
- uso de `MEM_MAPPED`;
- localización de direcciones XEX;
- herramientas recomendadas;
- tipos de parche admitidos;
- normas de formato;
- ejemplo de `.patch.toml`.

Antes de crear o validar el parche final, vuelve a consultar la versión actual de esta guía.

## Archivo oficial de parches de Kameo

Juego base:

https://github.com/xenia-canary/game-patches/blob/main/patches/4D5307D2%20-%20Kameo%20Elements%20of%20Power.patch.toml

TU2:

https://github.com/xenia-canary/game-patches/blob/main/patches/4D5307D2%20-%20Kameo%20Elements%20of%20Power%20(TU2).patch.toml

Comprueba siempre el contenido actual antes de editar o portar un parche.

## Documentación de plugins de Xenia Canary

https://github.com/xenia-canary/game-patches/blob/main/.github/Plugin-Docs.md

Los plugins XEX son experimentales. No los uses mientras un parche normal pueda resolver el problema.

## Opciones de Xenia Canary

https://github.com/xenia-canary/xenia-canary/wiki/Options

Consulta aquí las opciones actuales relacionadas con:

- parches;
- VSync;
- límite de FPS;
- CPU;
- logs;
- depuración;
- rutas de configuración;
- comportamiento portable.

## Guía rápida de Xenia Canary

https://github.com/xenia-canary/xenia-canary/wiki/Quickstart

## Repositorio de Xenia Canary

https://github.com/xenia-canary/xenia-canary

Cuando una opción, nombre de flag o comportamiento no esté claro, verifica el código fuente o la wiki actual. No inventes claves de configuración.

## XEX Loader para Ghidra

Fork mantenido:

https://github.com/SaveEditors/XEXLoaderWV

Este loader permite importar ejecutables XEX de Xbox 360 en Ghidra. Verifica la compatibilidad de la versión instalada de Ghidra, Java y la extensión antes de comenzar el análisis.

## Ghidra

Repositorio oficial:

https://github.com/NationalSecurityAgency/ghidra

Documentación de análisis headless:

https://ghidra.re/ghidra_docs/api/ghidra/app/util/headless/HeadlessAnalyzer.html

## Normas al consultar referencias

1. Registra la fecha o commit de la documentación utilizada cuando sea importante.
2. Distingue claramente entre:
   - hechos confirmados;
   - hipótesis;
   - inferencias;
   - resultados experimentales.
3. No copies direcciones de otra versión del juego sin portarlas y verificarlas.
4. No elimines ni cambies el hash para forzar compatibilidad.
5. No publiques ni incluyas archivos del juego en el repositorio.
