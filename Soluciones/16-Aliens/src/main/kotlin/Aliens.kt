package dev.joseluisgs.aliens

import kotlin.random.Random

/**
 * Simula el juego de los aliens
 * @param spaceOrigin Espacio con los aliens
 * @param marcador Array con el tiempo, vidas y aliens restantes
 * @return Espacio con los aliens
 */
fun simulacion(spaceOrigin: Space, marcador: IntArray): Space {
    var space = spaceOrigin

    do {
        // Doble buffer, origen --> destino
        val spaceBuffer = copySpace(space)
        println()
        println("Tiempo: ${marcador[TIEMP0]}")
        println("Vidas: ${marcador[VIDAS]}")
        println("Aliens restantes: ${marcador[ALIENS]}")
        printSpace(space)

        // Hay que tener en cuenta que al filtrar por tiempo, podemos hacer dos acciones
        // En el mismo turno, lo que nos puede provocar problemas en el doble buffer origen destino
        // Por eso, se puede hacer una acción por turno (ajustando tiempos),
        // o bien, copiar el espacio en cada acción

        // Cada 2 segundos disparamos
        if (marcador[TIEMP0] % TIME_TO_DEFEND == 0) {
            if (aimAndFire(space, spaceBuffer)) {
                marcador[ALIENS] -= 1
            }
            // Copiamos para que en el resto de condiciones no se pierda el disparo realizado
            //space = copySpace(spaceBuffer)
        }


        // Cada 3 segundos cambiamos de posición los alines
        if (marcador[TIEMP0] % TIME_TO_MOVE == 0) {
            // Los aliens se mueven aleatoramente (version cutre para aprobar)
            // spaceBuffer = createSpace(aliens)
            // Los aliens se mueven a posiciones adyacentes (version pro)
            moveAliens(space, spaceBuffer)
            println("Los aliens se han movido!")

            // Copiamos para que en el resto de condiciones no se pierda el movimiento realizado
            //space = copySpace(spaceBuffer)
        }

        // Cada 5 segundos los aliens atacan
        if (marcador[TIEMP0] % TIME_ATACK == 0) {
            if (alienAtack()) {
                marcador[VIDAS] -= 1
                println("Los aliens han atacado!")
                println("Vidas restantes: ${marcador[VIDAS]}")
            } else {
                println("Los aliens han fallado al atacar!")
            }
            // Copiamos para que en el resto de condiciones no se pierda el ataque realizado
            //space = copySpace(spaceBuffer)
        }


        Thread.sleep(PAUSE_TIME)
        marcador[TIEMP0] += 1
        // Doble buffer, destino --> origen
        space = copySpace(spaceBuffer)

    } while (marcador[TIEMP0] <= MAX_TIME && marcador[VIDAS] > 0 && marcador[ALIENS] > 0)
    return space
}

/**
 * Muestra el informe final del juego
 * @param space Espacio con los aliens
 * @param timeFinal Tiempo transcurrido
 * @param aliensFinal Número de aliens restantes
 * @param livesFinal Número de vidas restantes
 */
fun informeFinal(space: Space, timeFinal: Int, aliensFinal: Int, livesFinal: Int) {
    println()
    printSpace(space)
    println("Tiempo: $timeFinal")
    if (aliensFinal == 0) {
        println("Has aniquilado a todos los aliens!")
    } else {
        println("Hay aliens vivos que regresarán a por ti!")
        println("Aliens restantes: $aliensFinal")
    }
    if (livesFinal == 0) {
        println("Has muerto en esta batalla!")
    } else {
        println("Has sobrevivido, y vives para luchar otro día!")
        println("Vidas restantes: $livesFinal")
    }
}

/**
 * Mueve los aliens a posiciones adyacentes
 * @param space Espacio con los aliens
 * @param spaceBuffer Espacio con los aliens y los disparos
 */
fun moveAliens(space: Array<IntArray>, spaceBuffer: Space) {
    // Recorremos el espacio con los aliens
    for (i in space.indices) {
        for (j in space[i].indices) {
            if (space[i][j] == ALIEN) {
                // println("Alien se desplaza desde [${i + 1},${j + 1}]")
                moveAlienToANewPosition(spaceBuffer, i, j)
            }
        }
    }
}

/**
 * Mueve un alien a una posición adyacente
 * @param spaceBuffer Espacio con los aliens y los disparos
 * @param fil Fila del alien
 * @param col Columna del alien
 */
fun moveAlienToANewPosition(spaceBuffer: Array<IntArray>, fil: Int, col: Int) {
    // Obtenemos las posiciones adyacentes
    var isStored = false
    var newFil: Int
    var newCol: Int
    var intentos = 0
    do {
        newFil = (-1..1).random() + fil
        newCol = (-1..1).random() + col
        // Cuidado que es spaceBuffer porque comparamos con la nueva posición
        // si no se daría el caso de que el alien se mueve a una posición
        // que ya está ocupada por otro alien
        if (isValidPosition(newFil, newCol) && spaceBuffer[newFil][newCol] == VACIO) {
            spaceBuffer[fil][col] = VACIO
            spaceBuffer[newFil][newCol] = ALIEN
            isStored = true
        }
        intentos++
    } while (!isStored && intentos++ < MAX_TRIES_TO_MOVE)
    if (!isStored) {
        println("El alien en la posición [${fil + 1},${col + 1}] no se ha podido mover por estar bloqueado!")
    } else {
        println("Alien se desplaza desde [${fil + 1},${col + 1}] a [${newFil + 1},${newCol + 1}]")
    }
}

/**
 * Comprueba si la posición es válida
 * @param fil Fila
 * @param col Columna
 * @return true si la posición es válida, false en caso contrario
 */
fun isValidPosition(fil: Int, col: Int): Boolean {
    return fil in 0..<SPACE_SIZE && col in 0..<SPACE_SIZE
}

/**
 * Simula el disparo del jugador a una posición aleatoria
 * @param space Espacio con los aliens
 * @param spaceBuffer Espacio con los aliens y los disparos
 * @return true si ha acertado, false en caso contrario
 */
fun aimAndFire(space: Space, spaceBuffer: Space): Boolean {
    println("Apuntando...")
    val x = Random.nextInt(SPACE_SIZE)
    val y = Random.nextInt(SPACE_SIZE)
    if (space[x][y] == VACIO) {
        println("Has disparado a la posición [${x + 1},${y + 1}] y y es una posición vacía!")
        return false
    }
    return if (Random.nextInt(100) < PROB_ACCURACY) {
        println("Has disparado a la posición [${x + 1},${y + 1}] y has acertado!")
        spaceBuffer[x][y] = VACIO
        true
    } else {
        println("Has disparado a la posición [${x + 1},${y + 1}] y has fallado!")
        false
    }
}

/**
 * Simula el ataque de los aliens
 * @return true si han atacado, false en caso contrario
 */
fun alienAtack(): Boolean {
    return Random.nextInt(100) < PROB_ATACK
}

/**
 * Copia el espacio
 * @param space Espacio a copiar
 * @return Copia del espacio
 */
fun copySpace(space: Space): Space {
    val copy = Array(SPACE_SIZE) { IntArray(SPACE_SIZE) }
    for (i in space.indices) {
        for (j in space[i].indices) {
            copy[i][j] = space[i][j]
        }
    }
    return copy
}

/**
 * Muestra el espacio con los aliens
 * @param space Espacio con los aliens
 */
fun printSpace(space: Space) {
    for (row in space) {
        for (cell in row) {
            if (cell == ALIEN) {
                print("[A]")
            } else {
                print("[ ]")
            }
        }
        println()
    }
}

/**
 * Crea un espacio con los aliens en posiciones aleatorias.
 * @param numAlies Número de aliens a crear, por defecto es NUM_ALIENS
 * @return Espacio con los aliens
 */
fun createSpace(numAlies: Int = NUM_ALIENS): Space {
    val space = Array(SPACE_SIZE) { IntArray(SPACE_SIZE) }
    repeat(numAlies) {
        // No podemos poner dos aliens en la misma posición
        var isStored = false
        do {
            val x = (0 until SPACE_SIZE).random() // una forma de generar un número aleatorio
            val y = Random.nextInt(SPACE_SIZE) // y otra forma de generar un número aleatorio
            // Si la posición está vacía, la ocupamos
            if (space[x][y] == VACIO) {
                space[x][y] = ALIEN
                isStored = true
            }
        } while (!isStored)
    }
    return space
}
