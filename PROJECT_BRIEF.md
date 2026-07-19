# PROJECT_BRIEF.md

## Objetivo

Crear un parche para **Kameo: Elements of Power** en Xenia Canary que corrija el seguimiento automático de la cámara a 60 FPS.

## Comportamiento conocido

- A 30 FPS, la cámara sigue al personaje con la distancia prevista.
- A 60 FPS, la cámara sigue desde aproximadamente el doble de distancia.
- A unos 150 FPS, la cámara puede quedarse muy atrás mientras el personaje avanza.
- Al mover mínimamente el stick derecho, la cámara vuelve inmediatamente junto al personaje.
- El problema depende del framerate.
- El control manual de cámara funciona.
- El fallo parece afectar al cálculo o actualización automática del seguimiento.
- Ya existe un parche oficial que permite 60 FPS, pero no corrige la cámara.

## Hipótesis inicial

La lógica de seguimiento puede estar ligada a una suposición de 30 Hz o utilizar incorrectamente:

- `deltaTime`;
- un factor de interpolación;
- una velocidad de seguimiento;
- una distancia máxima;
- un temporizador;
- una actualización por frame.

Esta es una hipótesis, no una conclusión. Debe verificarse mediante análisis dinámico y estático.

## Resultado requerido

A 60 FPS:

- la distancia de cámara debe parecer equivalente a la original a 30 FPS;
- la velocidad de seguimiento debe ser equivalente;
- la cámara debe seguir durante avance recto sostenido;
- el stick derecho debe continuar funcionando correctamente;
- no debe haber oscilaciones, saltos, deriva ni zoom incorrecto;
- no debe haber crashes ni regresiones importantes.

## Alcance inicial

El objetivo principal es un parche fijo para 60 FPS.

Solo debe intentarse un arreglo dinámico para otros framerates si, una vez comprendida la función, puede hacerse sin aumentar sustancialmente el riesgo o la complejidad.

## Versiones del juego

No asumas qué versión está instalada.

Obtén del `xenia.log`:

- Title ID;
- módulo;
- Module Hash;
- Title Update, si existe.

Las direcciones de la versión base y de los Title Updates pueden ser diferentes. Nunca mezcles direcciones entre módulos o hashes distintos.

## Referencias conocidas del repositorio oficial

Estas referencias son informativas y deben confirmarse con los archivos actuales:

### Juego base

- Title ID: `4D5307D2`
- Hash conocido: `1A83A0AF5C0EDFE8`
- Dirección conocida del parche oficial de 60 FPS: `0x820cf93f`

### TU2

- Title ID: `4D5307D2`
- Hash conocido: `B94D3AB68548DF97`
- Dirección conocida del parche oficial de 60 FPS: `0x820d17c7`

## Prueba reproducible

Debes construir una prueba consistente usando:

- la misma partida;
- el mismo lugar;
- la misma transformación;
- la misma posición y orientación inicial;
- la misma posición de cámara;
- el mismo límite de FPS;
- la misma secuencia y duración de teclas;
- el mismo método de captura y comparación.

Debes crear primero una referencia a 30 FPS y reproducir después el fallo a 60 FPS.

## Validación mínima del candidato final

Prueba:

- avance recto sostenido;
- caminar y correr;
- giro manual de cámara;
- saltos;
- varias transformaciones;
- interiores;
- exteriores;
- colisión contra paredes;
- combate;
- transiciones;
- cutscenes;
- reinicio completo de Xenia;
- una sesión continuada razonable.

## Formato del parche final

El parche debe:

- corresponder al hash exacto;
- respetar el formato oficial de Xenia Canary;
- mantener intactos los parches existentes;
- usar minúsculas para direcciones y valores hexadecimales;
- quedar con `is_enabled = false` en la versión final;
- incluir nombre, descripción y autor;
- conservar una copia local habilitada para pruebas;
- documentar valores o instrucciones originales y nuevos.
