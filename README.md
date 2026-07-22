# Kameo Camera Fix for Xenia Canary

Corrige el seguimiento automático de la cámara de **Kameo: Elements of
Power** cuando el juego funciona a 60 o 100 FPS. El archivo contiene
exclusivamente estas dos opciones, completas y deshabilitadas por defecto:

- `60 FPS + Camera Fix`
- `100 FPS + Camera Fix`

Activa **solo una**. Las dos opciones son mutuamente excluyentes.

## Compatibilidad

- Emulador: Xenia Canary con soporte de game patches.
- Title ID: `4D5307D2`.
- Module Hash: `1A83A0AF5C0EDFE8`.
- Ejecutable: la versión de `default.xex` correspondiente exactamente a ese
  Module Hash.

Otras revisiones del juego no están soportadas por este archivo.

## Instalación

1. Cierra Xenia.
2. Haz una copia de seguridad del parche de Kameo que ya tengas instalado.
3. Copia `4D5307D2 - Kameo.patch.toml` a la carpeta `patches` de Xenia Canary,
   sustituyendo el archivo de Kameo existente para evitar entradas duplicadas.
4. Abre el gestor de parches y activa únicamente una de las dos opciones de
   framerate con Camera Fix.
5. Comprueba que los parches están habilitados en la configuración del juego.

> **Importante:** activa solo una opción Camera Fix y mantén la otra
> desactivada. Nunca actives ambas simultáneamente. Mantén `vsync = true`,
> `apply_patches = true` y usa el `framerate_limit` correspondiente.

## Configuración para 60 FPS

Activa únicamente `60 FPS + Camera Fix`, desactiva `100 FPS + Camera Fix` y
utiliza:

```toml
vsync = true
framerate_limit = 60
apply_patches = true
```

No actives simultáneamente la opción de 100 FPS.

## Configuración para 100 FPS

Activa únicamente `100 FPS + Camera Fix`, desactiva `60 FPS + Camera Fix` y
utiliza:

```toml
vsync = true
framerate_limit = 100
apply_patches = true
```

No actives simultáneamente la opción de 60 FPS.

No requiere un limitador externo de NVIDIA. El equipo debe ser capaz de
mantener aproximadamente 100 FPS para obtener el comportamiento previsto.

## Desinstalación

1. Cierra Xenia.
2. Desactiva las dos entradas Camera Fix.
3. Elimina el archivo instalado o restaura la copia de seguridad del parche
   oficial de Kameo.

El parche se aplica en memoria y no modifica `default.xex`, partidas, perfiles
ni contenido del juego.

## Limitaciones conocidas

- Solo se admite el Module Hash indicado.
- Cada opción está ajustada para su framerate concreto; no es un parche
  dinámico ni está diseñado para ejecución uncapped.
- No deben activarse ambas opciones a la vez.
- La validación manual inicial resultó estable, pero pueden existir cámaras o
  situaciones poco comunes que todavía no se hayan probado.

## Créditos

Camera Fix y paquete final: **ISpectre23**.
