plugins {
    kotlin("jvm")
}

dependencies {
    api(libs.jackson.annotations)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.asserions.core)
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
