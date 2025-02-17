plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":problem"))
}

kotlin {
    jvmToolchain(21)
}
