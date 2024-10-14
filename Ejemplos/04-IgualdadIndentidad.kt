package vectores

fun main() {
    val a = arrayListOf(1, 2, 3, 4, 5)
    val b = arrayListOf(1, 2, 3, 4, 5)
    val c = b

    println("a == b: ${a == b}") // igualdad de contenido, true
    println("a === b: ${a === b}") // igualdad de identidad, false

    println("a == c: ${a == c}") // igualdad de contenido, true
    println("a === c: ${a === c}") // igualdad de identidad, false

    println("b == c: ${b == c}") // igualdad de contenido, true
    println("b === c: ${b === c}") // igualdad de identidad, true

    if (a === b) {
        println("a y b son el mismo objeto")
    } else {
        println("a y b no son el mismo objeto")
    }

    if (a == b) {
        println("a y b tienen el mismo contenido")
    } else {
        println("a y b no tienen el mismo contenido")
    }

    var array1 = intArrayOf(1, 2, 3, 4, 5)
    var array2 = intArrayOf(1, 2, 3, 4, 5)

    // Son iguales pero no son indenticos
    println(array1 == array2) // true --> NO! (esta comparando direcciones de memoria))
    println(sonIguales(array1, array2)) // true
    println(array1.contentEquals(array2)) // true

    var array3 = array1
    println(array1 == array3) // true
    println(sonIguales(array1, array3)) // true
    println(array1.contentEquals(array3)) // true
}

fun sonIguales(array1: IntArray, array2: IntArray): Boolean {
    if (array1.size != array2.size) {
        return false
    }

    for (i in array1.indices) {
        if (array1[i] != array2[i]) {
            return false
        }
    }

    return true
}
