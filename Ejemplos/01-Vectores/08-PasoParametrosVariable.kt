fun main(args: Array<String>) {

    println(mendiaInfinita(1, 2, 3, 4, 5)) // 3.0
    println(mendiaInfinita(1, 2)) // 1.5
    var numeros = intArrayOf(1, 2, 3, 4, 5)
    println(mendiaInfinita(*numeros)) // 3.0


    // Puedo jugar con el numero de args del main
    // Si no paso nada, args es un array vacio
    // Si paso 1, args tiene un elemento
    println(args.size)
    for (arg in args) {
        println(arg)
    }
}

fun mendiaInfinita(vararg numeros: Int): Double {
    var suma = 0
    for (numero in numeros) {
        suma += numero
    }
    return suma.toDouble() / numeros.size
}