package dev.joseluisgs

import dev.joseluisgs.cesar.cifrarCesar

fun main() {
    println("Cifrado Cesar")
    val mensaje = "Hola 1 DAW estamos en lase de Programación. Disfrutando de los algoritmos"
    val desplazamiento = 28
    println(mensaje)
    val textoCifrado = cifrarCesar(mensaje, desplazamiento)
    println(textoCifrado)
    val textoDescifrado = cifrarCesar(textoCifrado, -desplazamiento)
    println(textoDescifrado)
}