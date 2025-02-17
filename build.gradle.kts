plugins {
    kotlin("jvm") version "1.9.25" apply false
    kotlin("plugin.allopen") version "1.9.25" apply false
    kotlin("plugin.jpa") version "1.9.25" apply false
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
    id("io.micronaut.application") version "4.4.4" apply false
    id("com.google.cloud.tools.jib") version "2.8.0" apply false
    id("io.micronaut.aot") version "4.4.4" apply false
}

subprojects {
    repositories { mavenCentral() }
}
