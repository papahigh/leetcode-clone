package io.github.papahigh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MainBackendApplication

fun main(args: Array<String>) {
    runApplication<MainBackendApplication>(*args)
}
