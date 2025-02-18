plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
    id("com.google.cloud.tools.jib")
    id("io.micronaut.aot")
}

version = "1.0"
group = "judge.service"

dependencies {
    implementation(project(":judge"))
    implementation(project(":problem"))
    implementation(project(":problem-stub"))
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.micrometer:micronaut-micrometer-annotation")
    kapt("io.micronaut.serde:micronaut-serde-processor")
    kapt("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-prometheus")
    implementation("io.micronaut.rabbitmq:micronaut-rabbitmq")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk8)
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.yaml:snakeyaml")
    testImplementation("io.micronaut:micronaut-http-client")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation(libs.testcontainers.rabbitmq)
}

application {
    mainClass = "judge.service.ApplicationKt"
}

java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}

kotlin {
    jvmToolchain(21)
}

allOpen {
    annotation("io.micronaut.rabbitmq.annotation.RabbitListener")
    annotation("jakarta.inject.Singleton")
}

tasks {
    jib {
        from { image = "paphigh/judge-runtime" }
    }

    dockerfileNative { jdkVersion = "21" }

    graalvmNative { toolchainDetection = false }

    named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") { jdkVersion = "21" }
}

micronaut {
    runtime("jetty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("judge.service.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}
