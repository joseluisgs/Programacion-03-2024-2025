package dev.joseluisgs

import dev.joseluisgs.loteria.generarCartonPrimitiva
import dev.joseluisgs.loteria.imprimirCarton

fun main() {
    println("Sorteo de la Primitiva")
    val boleto = generarCartonPrimitiva()
    println("Tu boleto es:")
    imprimirCarton(boleto)
}

