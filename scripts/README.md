# Scripts

Esta carpeta contiene únicamente automatización segura y reutilizable. Los
scripts deben escribir proyectos, bases de datos, capturas, logs y otros
resultados generados dentro de `_local/`.

## `ExportXdvdfsFile.java`

Extractor de solo lectura para copiar un archivo situado en la raíz de una
imagen XDVDFS de Xbox 360. La herramienta rechaza destinos existentes y elimina
una salida incompleta si falla.

Ejemplo, usando JDK 21 y escribiendo solo dentro de `_local/`:

```powershell
javac -d _local\temp\xdvdfs-classes scripts\ExportXdvdfsFile.java
java -cp _local\temp\xdvdfs-classes ExportXdvdfsFile `
  "D:\ruta\juego.iso" default.xex _local\game-files\default.xex
```
