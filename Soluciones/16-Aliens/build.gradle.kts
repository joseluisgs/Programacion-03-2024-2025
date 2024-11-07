plugins {
    kotlin("jvm") version "1.9.20"
    application
    id("org.jetbrains.dokka") version "1.9.20"
}

group = "dev.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

// Jar
tasks.jar {
    manifest {
        attributes["Main-Class"] = "dev.joseluisgs.aliens.MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    from(configurations.runtimeClasspath.get().map {
        if (it.isDirectory) it else zipTree(it)
    })

    archiveFileName.set("aliens.jar")

}