package dev.joseluisgs.aliens

const val SPACE_SIZE = 5
const val NUM_ALIENS = 10
const val ALIEN = 1
const val VACIO = 0
const val MAX_TIME = 30
const val PAUSE_TIME = 1000L // (1 segundo)
const val MAX_LIVES = 5
const val TIME_TO_DEFEND = 2
const val TIME_ATACK = 5
const val TIME_TO_MOVE = 3
const val PROB_ATACK = 40
const val PROB_ACCURACY = 70
const val MAX_TRIES_TO_MOVE = 16
const val TIEMP0 = 0
const val VIDAS = 1
const val ALIENS = 2


typealias Space = Array<IntArray>


fun main() {
    println("La guerra contra los Aliens ha comenzado!")

    // Preparamos el espacio
    var space = createSpace()

    // Simulamos el juego
    val marcador = intArrayOf(1, MAX_LIVES, NUM_ALIENS)
    space = simulacion(space, marcador)

    // Mostramos el resultado final
    informeFinal(space, marcador[TIEMP0], marcador[ALIENS], marcador[VIDAS])
}
