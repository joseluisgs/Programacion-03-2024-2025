package dev.joseluisgs

fun main() {
    val dni1 = "12345678Z"
    val dni2 = "01234567z"
    val dni3 = "123456"
    val text1 = "DNI: $dni1$dni2$dni3"

    // Crear la expresión regular
    val regex = "[0-9]{8}[A-Za-z]".toRegex()

    // Si alguna de las partes del DNI no cumple la expresión regular
    if (regex.matches(dni1)) {
        println("DNI1: $dni1 es correcto")
    } else {
        println("DNI1: $dni1 no es correcto")
    }
    if (regex.matches(dni2)) {
        println("DNI2: $dni2 es correcto")
    } else {
        println("DNI2: $dni2 no es correcto")
    }
    if (regex.matches(dni3)) {
        println("DNI3: $dni3 es correcto")
    } else {
        println("DNI3: $dni3 no es correcto")
    }

    // Cuantas veces aparece la expresión regular en el texto
    val matches = regex.findAll(text1)
    println("Aparece: ${matches.count()}")

    // Mostrar las ocurrencias
    for (match in matches) {
        println("Encontrado: ${match.value}")
    }

    // Partir por la expresión regular
    val parts = regex.split(text1).toTypedArray()
    for (part in parts) {
        println("Parte: $part")
    }

    // Ahora con matrículas, con guión opcional
    val matricula1 = "1234-BBC"
    val matricula2 = "1234bbc"
    val matricula3 = "1234-bbc"
    val matricula4 = "1234TZV"
    val matricula5 = "1234-ABCD"
    val matricula6 = "134-FFD"

    val regexMatricula = """\d{4}-?[BCDFGHJKLMNPRSTVWXYZ]{3}""".toRegex()

    if (regexMatricula.matches(matricula1)) {
        println("Matricula1: $matricula1 es correcto")
    } else {
        println("Matricula1: $matricula1 no es correcto")
    }

    if (regexMatricula.matches(matricula2)) {
        println("Matricula2: $matricula2 es correcto")
    } else {
        println("Matricula2: $matricula2 no es correcto")
    }

    if (regexMatricula.matches(matricula3)) {
        println("Matricula3: $matricula3 es correcto")
    } else {
        println("Matricula3: $matricula3 no es correcto")
    }

    if (regexMatricula.matches(matricula4)) {
        println("Matricula4: $matricula4 es correcto")
    } else {
        println("Matricula4: $matricula4 no es correcto")
    }

    if (regexMatricula.matches(matricula5)) {
        println("Matricula5: $matricula5 es correcto")
    } else {
        println("Matricula5: $matricula5 no es correcto")
    }

    if (regexMatricula.matches(matricula6)) {
        println("Matricula6: $matricula6 es correcto")
    } else {
        println("Matricula6: $matricula6 no es correcto")
    }

    // Leer matrícula
    //val matricula = leerMatricula()
    //println("Matrícula introducida: $matricula")

    // Leer fecha de nacimiento
    val fecha = leerFechaNacimiento()
    println("Fecha introducida: $fecha")

}

fun leerMatricula(): String {
    var matricula: String
    val regexMatricula = """\d{4}-?[BCDFGHJKLMNPRSTVWXYZ]{3}""".toRegex()
    var matriculaCorrecta = false
    do {
        println("Introduce la matrícula:")
        matricula = readln()
        if (regexMatricula.matches(matricula)) {
            matriculaCorrecta = true
        } else {
            println("Matrícula incorrecta, inserte una matrícula correcta formato 'XXXX-XXX' o 'XXXXXXX' (sin guion).")
        }
    } while (!matriculaCorrecta)
    return matricula
}

fun leerFechaNacimiento(): String {
    var fecha: String
    //val regexFecha = """\d{2}/\d{2}/\d{4}""".toRegex()
    val regexFecha =
        """^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/((19[0-9]{2})|(200[0-9])|(201[0-9])|(202[0-2]))$""".toRegex()
    var fechaCorrecta = false
    do {
        println("Introduce la fecha de nacimiento:")
        fecha = readln()
        if (regexFecha.matches(fecha)) {
            fechaCorrecta = true
        } else {
            println("Fecha incorrecta, inserte una fecha correcta formato 'dd/MM/yyyy'.")
        }
    } while (!fechaCorrecta)
    return fecha
}

fun analizarFecha(fecha: String): Boolean {
    val partes = fecha.split("/")
    val dia = partes[0].toInt()
    val mes = partes[1].toInt()
    val anio = partes[2].toInt()
    if (dia < 1 || dia > 31) {
        println("Día incorrecto")
        return false
    }
    if (mes < 1 || mes > 12) {
        println("Mes incorrecto")
        return false
    }
    if (anio < 1900 || anio > 2021) {
        println("Año incorrecto")
        return false
    }
    return true
}
