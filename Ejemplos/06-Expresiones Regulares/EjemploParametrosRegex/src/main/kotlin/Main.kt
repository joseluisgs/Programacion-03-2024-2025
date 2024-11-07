/*
Me llamo Antonio y he realizado la práctica de Kotlin.
 */
package dev.joseluisgs

import kotlin.system.exitProcess

/**
 * Programa que crea una matriz de filas y columnas con un valor inicial
 * que se pasa por argumentos.
 * @param args Argumentos de entrada
 */
fun main(args: Array<String>) {
    /*analizarArgumentosBasico(args)
    // hacer una matriz de de filas columnas y inicializarla con el valor de los argumentos
    val filas = args[0].toInt()
    val columnas = args[1].toInt()
    val valor = args[2].toInt()
    var matrix = Array(filas) { Array(columnas) { valor } }
    // Mostrar la matriz
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            print("${matrix[i][j]} ")
        }
        println()
    }*/
    println()
    val valores = analizarArgumentosAvanzado(args)
    println("Filas: ${valores[0]}, Columnas: ${valores[1]}, Valor: ${valores[2]}")
    val matrix = Array(valores[0]) { Array(valores[1]) { valores[2] } }
    // Mostrar la matriz
    for (i in matrix.indices) {
        for (j in matrix[i].indices) {
            print("${matrix[i][j]} ")
        }
        println()
    }
}

/**
 * Analiza los argumentos de entrada avanzado
 * @param args Argumentos de entrada
 * @return Un array con los valores de filas, columnas y valor
 * @author Jose Luis
 * @version 1.0
 * @see analizarArgumentosBasico
 */
fun analizarArgumentosAvanzado(args: Array<String>): IntArray {
    val valores = IntArray(3)

    if (args.size < 3) {
        println("Debe ingresar al menos tres argumentos.")
        exitProcess(1)
    }

    val regex01 = """filas:\d+""".toRegex()
    val regex02 = """columnas:\d+""".toRegex()
    val regex03 = """valor:\d+""".toRegex()

    if (!args[0].matches(regex01) || !args[1].matches(regex02) || !args[2].matches(regex03)) {
        println("Los argumentos no tienen el formato correcto.")
        exitProcess(1)
    }
    valores[0] = args[0].split(":")[1].toInt() // esta sirve para todos
    valores[1] = args[1].substring(9).toInt()
    valores[2] = args[2].substringAfter("valor:").toInt()
    return valores
}

/**
 * Analiza los argumentos de entrada básico
 * @param argumentos Argumentos de entrada
 * @return Un array con los valores de filas, columnas y valor
 */
fun analizarArgumentosBasico(argumentos: Array<String>) {
    if (argumentos.size < 3) {
        println("Debe ingresar al menos tres argumentos.")
        exitProcess(1)
    }
    // Los tres deben ser enteros y mayores a 0
    val intRegex = "\\d+".toRegex()
    for (i in argumentos.indices) {
        if (!argumentos[i].matches(intRegex)) {
            println("El argumento ${argumentos[i]} no es un número entero.")
            exitProcess(1)
        }
    }
}
