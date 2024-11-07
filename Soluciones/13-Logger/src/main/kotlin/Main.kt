package dev.joseluisgs

import org.lighthousegames.logging.logging

fun main() {
    val logger = logging()
    println("Hello World!")
    logger.debug { "Hello Debug!" }
    logger.info { "Hello Info!" }
    logger.warn { "Hello Warn!" }
    logger.error { "Hello Error!" }
    println("Bye World!")
}