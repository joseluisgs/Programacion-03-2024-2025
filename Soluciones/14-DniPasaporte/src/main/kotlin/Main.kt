package dev.joseluisgs

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import org.lighthousegames.logging.logging


val terminal = Terminal()
val log = logging()
fun main() {
    println(TextColors.green("Validador de DNI y Pasaporte"))

    menuAplicacion()

}

/**
 * Muestra el menú principal de la aplicación
 */
fun menuAplicacion() {
    var opcion: Int = 0
    val regex = Regex("^[1-3]$")
    do {
        println("1. Validar DNI")
        println("2. Validar Pasaporte")
        println("3. Salir")
        print("Introduce una opción: ")
        val input = readln()
        if (regex.matches(input)) {
            opcion = input.toInt()
        } else {
            println("Opción no válida. Inténtelo de nuevo.")
        }
        when (opcion) {
            1 -> validarDNI()
            2 -> validarPasaporte()
            3 -> salir()
        }
    } while (opcion != 3)
}

/**
 * Valida un Pasaporte
 */
fun validarPasaporte() {
    println("Introduce el Pasaporte a validar:")
    var pasaporteString = readln().trim().uppercase()
    var valido = false
    //valido = validarPasaporteRegex(pasaporteString)
    valido = validarPasaporteManual(pasaporteString)
    if (valido) {
        println(TextColors.green("El Pasaporte $pasaporteString es válido."))
    } else {
        println(TextColors.red("El Pasaporte $pasaporteString no es válido."))
    }
}

/**
 * Valida un Pasaporte manualmente
 * @param pasaporteString Pasaporte a validar
 * @return true si es válido, false en caso contrario
 * @see validarPasaporteRegex
 */
fun validarPasaporteManual(pasaporteString: String): Boolean {
    log.info { "Validando Pasaporte: $pasaporteString" }
    var res = false
    val tabla = "TRWAGMYFPDXBNJZSQVHLCKE"
    val alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    if (pasaporteString.length == 9
        && pasaporteString.substring(3, 9).toIntOrNull() != null
        && pasaporteString.substring(9) in tabla
    ) {

        val letras = pasaporteString.substring(0, 3) // Obtenemos la letra
        val numeros = pasaporteString.substring(3, 9) // Obtenemos los números
        val letra = pasaporteString.substring(9) // Obtenemos la letra

        log.debug { "Letras: $letras, Números: $numeros, Letra: $letra" }

        // Pasar cada letra al indice correspondiente del alfabeto y concatenar
        val numerosConcatenados = StringBuilder()

        for (item in letras) {
            val indice = alfabeto.indexOf(item)
            if (indice == -1) {
                log.error { "Letra no válida: $item" }
                return false
            } else {
                numerosConcatenados.append(indice)
            }
        }
        log.debug { "Letras a Números: $numerosConcatenados" }
        val numeroFinal = numerosConcatenados.append(numeros)
        log.debug { "Números Concatenados: $numeroFinal" }
        val numero = numeroFinal.toString().toInt()
        log.debug { "Números Final: $numero" }
        val resto = numero % 23 // Calculamos el resto
        val letraCalculada = tabla[resto] // Obtenemos la letra calculada
        log.debug { "Calculada: $letraCalculada" }
        res = letraCalculada == letra[0]
    }
    return res
}

/**
 * Valida un Pasaporte manualmente
 * @param pasaporteString Pasaporte a validar
 * @return true si es válido, false en caso contrario
 */
fun validarPasaporteRegex(pasaporteString: String): Boolean {
    log.info { "Validando Pasaporte: $pasaporteString" }
    var res = false
    val tabla = "TRWAGMYFPDXBNJZSQVHLCKE"
    val alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val regex = Regex("^[ABCDEFGHIJKLMNOPQRSTUVWXYZ]{3}[0-9]{6}[TRWAGMYFPDXBNJZSQVHLCKE]$")
    // Si coincide con la expresión regular
    if (regex.matches(pasaporteString)) {
        val letras = pasaporteString.substring(0, 3) // Obtenemos la letra
        val numeros = pasaporteString.substring(3, 9) // Obtenemos los números
        val letra = pasaporteString.substring(9) // Obtenemos la letra
        log.debug { "Letras: $letras, Números: $numeros, Letra: $letra" }

        // Pasar cada letra al indice correspondiente del alfabeto y concatenar
        val numerosConcatenados = StringBuilder()

        for (item in letras) {
            val indice = alfabeto.indexOf(item)
            numerosConcatenados.append(indice)
        }
        log.debug { "Letras a Números: $numerosConcatenados" }
        val numeroFinal = numerosConcatenados.append(numeros)
        log.debug { "Números Concatenados: $numeroFinal" }
        val numero = numeroFinal.toString().toInt()
        log.debug { "Números Final: $numero" }
        val resto = numero % 23 // Calculamos el resto
        val letraCalculada = tabla[resto] // Obtenemos la letra calculada
        log.debug { "Calculada: $letraCalculada" }
        res = letraCalculada == letra[0]
    }
    return res
}

/**
 * Valida un DNI
 */
fun validarDNI() {
    println("Introduce el DNI a validar:")
    var dniString = readln().trim().uppercase()
    var valido = false
    //valido = validarDniRegex(dniString)
    valido = validarDniManual(dniString)
    if (valido) {
        println(TextColors.green("El DNI $dniString es válido."))
    } else {
        println(TextColors.red("El DNI $dniString no es válido."))
    }
}

/**
 * Valida un DNI manualmente
 * @param dniString DNI a validar
 * @return true si es válido, false en caso contrario
 * @see validarDniRegex
 */
fun validarDniManual(dniString: String): Boolean {
    log.info { "Validando DNI: $dniString" }
    var res = false
    val tabla = "TRWAGMYFPDXBNJZSQVHLCKE"
    // Si coincide con la expresión regular
    if (dniString.length == 9 &&
        dniString.substring(0, 8).toIntOrNull() != null &&
        dniString.substring(8) in tabla
    ) {
        val dni = dniString.substring(0, 8).toInt()  // Obtenemos los 8 primeros dígitos
        val letra = dniString.substring(8) // Obtenemos la letra
        val resto = dni % 23 // Calculamos el resto
        val letraCalculada = tabla[resto] // Obtenemos la letra calculada
        log.debug { "DNI: $dni, Letra: $letra, Calculada: $letraCalculada" }
        res = letraCalculada == letra[0]
    }
    return res
}

/**
 * Valida un DNI con una expresión regular
 * @param dniString DNI a validar
 * @return true si es válido, false en caso contrario
 */
fun validarDniRegex(dniString: String): Boolean {
    log.info { "Validando DNI: $dniString" }
    var res = false
    val regex = Regex("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$")
    val tabla = "TRWAGMYFPDXBNJZSQVHLCKE"
    // Si coincide con la expresión regular
    if (regex.matches(dniString)) {
        val dni = dniString.substring(0, 8).toInt()  // Obtenemos los 8 primeros dígitos
        val letra = dniString.substring(8) // Obtenemos la letra
        val resto = dni % 23 // Calculamos el resto
        val letraCalculada = tabla[resto] // Obtenemos la letra calculada
        log.debug { "DNI: $dni, Letra: $letra, Calculada: $letraCalculada" }
        res = letraCalculada == letra[0]
    }
    return res
}

/**
 * Sale del programa
 */
fun salir() {
    println("Hasta la próxima!")
}
