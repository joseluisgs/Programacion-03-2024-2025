package dev.joseluisgs.cesar

/**
 * Cifra un mensaje con el cifrado de Cesar
 * @param mensaje Mensaje a cifrar
 * @param desplazamiento Desplazamiento a aplicar
 * @return Mensaje cifrado
 */
fun cifrarCesar(mensaje: String, desplazamiento: Int): String {
    val alafabeto =
        " " +
                "abcdefghijklmnñopqrstuvwxyz" +
                "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890" +
                ".,;:?¡!()[]{}@#\$%&/\\\"'çÇáéíóúÁÉÍÓÚ" +
                "àèìòùÀÈÌÒÙâêîôûÂÊÎÔÛäëïöüÄËÏÖÜãõÃÕ"

    val sb = StringBuilder()

    val desplazamiento = desplazamiento % alafabeto.length

    for (caracter in mensaje) {
        val posicion = alafabeto.indexOf(caracter)
        if (posicion == -1) {
            sb.append(caracter)
        } else {
            if (posicion + desplazamiento < 0) {
                sb.append(alafabeto[alafabeto.length + (posicion + desplazamiento)])
            } else {
                sb.append(alafabeto[(posicion + desplazamiento) % alafabeto.length])
            }
        }
    }
    return sb.toString()
}