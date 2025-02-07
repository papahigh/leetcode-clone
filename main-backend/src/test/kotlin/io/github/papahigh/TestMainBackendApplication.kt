package io.github.papahigh

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<MainBackendApplication>().with(TestcontainersConfiguration::class).run(*args)
}
