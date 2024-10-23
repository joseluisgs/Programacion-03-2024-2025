package dev.joseluisgs

fun main() {
    // Tipos no nulos, si no pones el valor se considera el valor por defecto
    var edad: Int = 18
    var nombre: String = "Jose"
    var altura: Double = 1.75

    println("Hola $nombre, tienes $edad años y mides $altura")
    //edad = null

    // Tipos nulos, si no pones el valor se considera nulo
    var edad2: Int? = 18
    var nombre2: String? = null
    var altura2: Double? = null

    println(edad2)
    println(altura2)
    println(nombre2)

    // Es .? para acceder a propiedades de objetos nulos (operador de acceso seguro)
    println(nombre2?.length)

    // que equivalente a if
    if (nombre2 != null) {
        println(nombre2.length)
    }

    // si usamos !! y es nulo, nos dará una excepción, tú te lo hasbuscado
    //println(nombre2!!.length)

    // Podemos usar (operador Elvis) ?: para usar un valor por defecto en caso de que sea nulo
    println(nombre2?.length ?: "No tiene nombre")

    // Que es el equivalente a un if
    if (nombre2 != null) {
        println(nombre2.length)
    } else {
        println("No tiene nombre")
    }

    // cuando leemos de teclado
    //var edadInput: Int = readln().toInt() // No es seguro, puede dar excepción si meto una letra
    //var edadInput2: Int? = readln().toIntOrNull() // Es seguro, si meto una letra me devuelve null
    //var edadInput3: Int = readln().toIntOrNull() ?: 0 // Es seguro, si meto una letra me devuelve 0

    // Leer un entero con seguridad
    var miNumero = leerEnteroRecursivo("Introduce un número:")
    println("El número introducido es: $miNumero")
    miNumero = leerEntero("Introduce un número:")
    println("El número introducido es: $miNumero")
}

fun leerEntero(mensaje: String): Int {
    do {
        println(mensaje)
        val input = readln().toIntOrNull()
        if (input != null) {
            return input
        } else {
            println("No has introducido un número correcto")
        }
    } while (true)
}

fun leerEnteroRecursivo(mensaje: String): Int {
    println(mensaje)
    return readln().toIntOrNull() ?: leerEnteroRecursivo(mensaje)
}
