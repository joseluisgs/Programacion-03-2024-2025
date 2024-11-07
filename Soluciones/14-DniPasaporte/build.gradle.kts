plugins {
    kotlin("jvm") version "2.0.20"
    // Para la documentación
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "dev.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Librerías para Logging
    implementation("org.lighthousegames:logging:1.3.0") // Librería de Logging
    implementation("ch.qos.logback:logback-classic:1.4.14") // Implementación de Logback


    // Mordant
    implementation("com.github.ajalt.mordant:mordant:2.2.0")
    implementation("net.java.dev.jna:jna:5.13.0")

    // Test
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

// Jar
tasks.jar {
    manifest {
        attributes["Main-Class"] = "dev.joseluisgs.MainKt"
    }
    // Copia de las librerías a la carpeta lib
    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    })
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    archiveFileName.set("dni-pasaporte.jar")
}