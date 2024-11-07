# Practica FMM

El modelo FFM (Forest-­‐FireModel) es un sistema dinámico inspirado en cómo se quema un bosque.

La práctica consistirá en implementar dicho modelo para, entre otras cosas, conseguir un programa que muestre por pantalla cómo evoluciona un busque bajo las condiciones del modelo.

## El modelo FFM

El modelo FFM consiste en una cuadrícula de celdas que pueden tener tres estados posibles:
- vacía
- ocupada por un árbol
- ardiendo 

La evolución del sistema en el tiempo viene determinada por cuatro reglas que se ejecutan simultáneamente:

1. Una celda ardiendo se convierte en un espacio vacío
2. Un árbol arderá si al menos un árbol vecino suyo está ardiendo
3. Un árbol comienza a arder con probabilidad f incluso si no tiene ningún vecino ardiendo
4. Un árbol brota en un espacio vacío con probabilidad p

Simple, ¿verdad?

El tamaño de la cuadrícula será entre 100 y 150 y se pasará por parámetros, el número de árboles iniciales, se insertará por parámetros y será entre el 30% y el 80% del tamaño de la cuadrícula, el tiempo máximo de simulación en segundos, que irá entre 60 y 120 y finalmente las probabilidad de arder y crecer se pasarán por parámetros, sy si no se pasan (son opcionales) se tomará el valor:
- PARDER = 0.00006
- PCRECER =0.01

Recuerda que se puede cambiar el orden de los parámetros en la llamada:

Deberás hacer un jar para su ejecución y poder llamarlo de la siguiente manera:

```shell
java -jar FFM.jar cuadricula:100 arboles:50 tiempo:120 parder:0.00006 pcrecer:0.01
```

Deberás tener estas funciones
- Tendrá una función `step` que realizará un paso en la simulación del bosque. En cada paso, se aplicarán las reglas del modelo FFM a cada celda de la cuadrícula aplicando doble buffering.
- La función `print` mostrará por pantalla el estado actual del bosque. Cada celda se representará con un carácter, siendo:
  - ` ` (espacio) para celda vacía  (o negro).
  - `🌳` para celda con árbol (o verde).
  - `🔥` para celda ardiendo (o rojo/naranja).
- La operación tal que dadas dos coordenadas, indican si alguna de las celdas vecinas está ardiendo será una operación `hasBurningNeighbour`.

Como informe final, deberás obtener:
- Número de celdas que han ardido.
- Número de árboles que han nacido.
- Número de celdas vacías.
- Listado de celdas que tiene un árbol al final de la interacción.

Deberás utilizar logger para información importante y clases que manejen la consola y el color para la celda o emoticonos. Cada elemento del software deberá estar documentado y generar la documentación con Dokka y KotlinDoc/JavaDoc.

Deberás hacer un informe en README.MD indicando las partes más relevantes de vuestra implementación.
