plugins {
    kotlin("jvm") version "2.1.10"
}

dependencies {
    api(project(":problem"))
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}

