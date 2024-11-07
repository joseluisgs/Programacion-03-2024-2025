import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import org.lighthousegames.logging.logging

const val TAM = 5
const val LIBRE = false
const val OCUPADO = true
const val PRECIO = 5.50

typealias Sala = Array<BooleanArray> // Alias de tipo

val t = Terminal()
val log = logging()

fun main() {
    var opcion: Int
    val sala: Sala = Array(TAM) { BooleanArray(TAM) { LIBRE } }
    println("Bienvenido al cine")
    println("=====================")
    println()
    do {
        println("1. Mostrar sala")
        println("2. Comprar entrada")
        println("3. Devolver entrada")
        println("4. Recaudación total")
        println("5. Salir")
        print("Ingrese una opción: ")
        opcion = readln().toIntOrNull() ?: 0
        when (opcion) {
            1 -> mostrarSala(sala)
            2 -> comprarEntrada(sala)
            3 -> devolverEntrada(sala)
            4 -> recaudacion(sala)
            5 -> println("¡Hasta luego!")
            else -> println("Opción no válida")
        }
        println()
    } while (opcion != 5)
}

/**
 * Recaudación total de la sala
 * @param sala Sala de cine
 */
fun recaudacion(sala: Sala) {
    log.info { "Calculando recaudación" }
    println()
    println("Recaudación")
    var total: Double = 0.0
    // Recorremos
    for (fila in sala.indices) {
        for (columna in sala[fila].indices) {
            if (sala[fila][columna] == OCUPADO) {
                total += PRECIO
            }
        }
    }
    println(TextColors.blue("Total de recaudació1n: $total €"))
    println()
}

fun devolverEntrada(sala: Sala) {
    log.info { "Devolviendo entrada" }
    var isCorrecto: Boolean
    val filasLetras = "ABCDE"
    println()
    do {
        println("Ingrese la fila y la columna a devolver (Fila:Columna, Ejemplo: A:2): ")
        val regex = Regex("^[A-E]:[1-5]$")
        val input = readln().trim().uppercase()
        if (!regex.matches(input)) {
            println("No has introducido una butaca válida")
            isCorrecto = false
        } else {
            val fila = filasLetras.indexOf(input[0])
            val columna = input[2].toString().toInt() - 1
            if (!isOcupada(sala, fila, columna)) {
                println("La butaca $input no está ocupada")
                isCorrecto = false
            } else {
                liberarButaca(sala, fila, columna)
                isCorrecto = true
            }
        }
    } while (!isCorrecto)
}

/**
 * Liberar una butaca
 * @param sala Sala de cine
 * @param fila Fila
 * @param columna Columna
 */
fun liberarButaca(sala: Sala, fila: Int, columna: Int) {
    log.info { "Liberando butaca $fila:$columna" }
    sala[fila][columna] = LIBRE
    println("Butaca liberada correctamente")
}

/**
 * Comprar una entrada
 * @param sala Sala de cine
 */
fun comprarEntrada(sala: Sala) {
    log.info { "Comprando entrada" }
    println()
    if (!hayButacaLibre(sala)) {
        println(TextColors.red("No hay butacas libres"))
        return
    }
    var isCorrecto: Boolean
    val filasLetras = "ABCDE"
    do {
        println("Ingrese la fila y la columna a comprar (Fila:Columna, Ejemplo: A:2): ")
        val regex = Regex("^[A-E]:[1-5]$")
        val input = readln().trim().uppercase()
        if (!regex.matches(input)) {
            println(TextColors.red("No has introducido una butaca válida"))
            isCorrecto = false
        } else {
            val fila = filasLetras.indexOf(input[0])
            val columna = input[2].toString().toInt() - 1
            if (isOcupada(sala, fila, columna)) {
                println(TextColors.red("La butaca $input está ocupada"))
                isCorrecto = false
            } else {
                ocuparButaca(sala, fila, columna)
                isCorrecto = true
            }
        }
    } while (!isCorrecto)

}

/**
 * Ocupar una butaca
 * @param sala Sala de cine
 * @param fila Fila
 * @param columna Columna
 */
fun ocuparButaca(sala: Sala, fila: Int, columna: Int) {
    log.info { "Ocupando butaca $fila:$columna" }
    sala[fila][columna] = OCUPADO
    println(TextColors.green("Butaca ocupada correctamente"))
}

/**
 * Comprueba si una butaca está ocupada
 * @param sala Sala de cine
 * @param fila Fila
 * @param columna Columna
 */
fun isOcupada(sala: Sala, fila: Int, columna: Int): Boolean {
    log.info { "Comprobando si la butaca $fila:$columna está ocupada" }
    return sala[fila][columna] == OCUPADO
}

/**
 * Mostramos la sala por pantalla
 * @param sala Sala de cine
 */
fun mostrarSala(sala: Sala) {
    log.info { "Mostrando sala" }
    var letrasFilas = "ABCDEFGHIJ"
    println()
    println("Estado de la sala de cine")
    // Mostramos las columnas
    print("  ")
    for (i in 1..TAM) {
        print("  $i  ")
    }
    println()
    for (fila in sala.indices) {
        print("${letrasFilas[fila]} ")
        for (columna in sala[fila].indices) {
            if (sala[fila][columna] == OCUPADO) {
                print("| X |")
            } else {
                print("| _ |")
            }
        }
        println()
    }
    println()

    t.println(
        table { // tabla
            header { // Cabecera
                row { // Fila
                    cell("") // Celda vacía
                    for (i in 1..TAM) {
                        cell(i.toString()) // Celda con el número de la columna
                    }
                }
            }
            body { // Cuerpo
                for (fila in sala.indices) { // Recorremos las filas
                    row { // Fila
                        cell(letrasFilas[fila].toString()) // Celda con la letra de la fila
                        for (columna in sala[fila].indices) { // Recorremos las columnas
                            if (sala[fila][columna] == OCUPADO) { // Si la butaca está ocupada
                                cell("X") {
                                    style = TextColors.brightGreen // Celda con una X en verde
                                } // Celda con una X
                            } else {
                                cell("_") {
                                    style = TextColors.brightRed // Celda con un guión bajo en rojo
                                } // Celda con un guión bajo
                            }
                        }
                    }
                }
            }
        }
    )
}

/**
 * Comprobamos si hay alguna butaca libre en la sala
 * @param sala Sala de cine
 * @return true si hay alguna butaca libre, false si la sala está completa
 */
fun hayButacaLibre(sala: Sala): Boolean {
    log.info { "Comprobando si hay butacas libres" }
    for (fila in sala.indices) {
        for (columna in sala[fila].indices) {
            if (sala[fila][columna] == LIBRE) {
                return true
            }
        }

    }
    return false
}
