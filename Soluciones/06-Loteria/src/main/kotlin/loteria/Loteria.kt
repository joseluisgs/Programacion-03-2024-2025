package dev.joseluisgs.loteria

/**
 * Genera un cartón de la primitiva
 * @return Cartón de la primitiva con 6 números aleatorios sin repetirse
 */
fun generarCartonPrimitiva(): IntArray {
    val carton = IntArray(6)
    var numero: Int
    for (i in 0..<6) {
        do {
            numero = numeroAleatorio(1, 49)
        } while (existeNumeroEnCarton(numero, carton))
        carton[i] = numero
    }
    return carton
}

/**
 * Genera un número aleatorio
 * @param min Mínimo
 * @param max Máximo
 * @return Número aleatorio
 */
fun numeroAleatorio(min: Int, max: Int): Int {
    return (min..max).random()
}

/**
 * Comprueba si un número está en un cartón
 * @param numero Número a buscar
 * @param carton Cartón en el que buscar
 * @return Si el número está en el cartón
 */
fun existeNumeroEnCarton(numero: Int, carton: IntArray): Boolean {
    for (element in carton) {
        if (element == numero) {
            return true
        }
    }
    return false
}

/**
 * Imprime un cartón
 * @param carton Cartón a imprimir
 */
fun imprimirCarton(carton: IntArray) {
    for (element in carton) {
        print("$element ")
    }
    println()
}