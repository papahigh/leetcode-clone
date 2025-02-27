import cz.habarta.typescript.generator.EnumMapping
import cz.habarta.typescript.generator.JsonLibrary
import cz.habarta.typescript.generator.StringQuotes
import cz.habarta.typescript.generator.TypeScriptFileType
import cz.habarta.typescript.generator.TypeScriptGenerator
import cz.habarta.typescript.generator.TypeScriptOutputKind

plugins {
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen")
    kotlin("plugin.jpa")
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
    id("com.google.cloud.tools.jib")
    id("io.micronaut.aot")
    id("cz.habarta.typescript-generator") version "3.2.1263"
}

version = "1.0"
group = "main.backend"

dependencies {

    implementation(libs.mapstruct.core)
    kapt(libs.mapstruct.processor)

    implementation(project(":judge"))
    implementation(project(":problem"))
    implementation(project(":problem-stub"))
    implementation(libs.hypersistence.tsid)
    kapt("io.micronaut.data:micronaut-data-processor")
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.openapi:micronaut-openapi")
    kapt("io.micronaut.serde:micronaut-serde-processor")
    kapt("io.micronaut.servlet:micronaut-servlet-processor")
    kapt("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.data:micronaut-data-jdbc")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.liquibase:micronaut-liquibase")
    implementation("io.micronaut.micrometer:micronaut-micrometer-core")
    implementation("io.micronaut.micrometer:micronaut-micrometer-registry-prometheus")
    implementation("io.micronaut.rabbitmq:micronaut-rabbitmq")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk8)
    compileOnly("io.micronaut:micronaut-http-client")
    compileOnly("io.micronaut.openapi:micronaut-openapi-annotations")
    compileOnly("jakarta.persistence:jakarta.persistence-api")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly(libs.liquibase.slf4j)
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.yaml:snakeyaml")
    testImplementation("io.micronaut:micronaut-http-client")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:mysql")
    testImplementation("org.testcontainers:testcontainers")
    testImplementation(libs.testcontainers.rabbitmq)
}

application {
    mainClass = "main.backend.ApplicationKt"
}

java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}

kotlin {
    jvmToolchain(21)
}

allOpen {
    annotation("io.micronaut.http.annotation.Controller")
    annotation("io.micronaut.rabbitmq.annotation.RabbitListener")
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.inject.Singleton")
}

tasks {
    jib {
        from { image = "openjdk:21-slim-bookworm" }
    }

    dockerfileNative { jdkVersion = "21" }

    graalvmNative { toolchainDetection = false }

    named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") { jdkVersion = "21" }

    generateTypeScript {
        classes = listOf(
            "main.backend.problem.ProblemSummary",
            "main.backend.problem.ProblemDetails",
            "main.backend.submission.CreateSubmission",
            "main.backend.submission.SubmissionDetails",
        )

        excludeClasses = listOf(
            "java.io.Serializable",
        )
        excludeClassPatterns = listOf(
            "**\$Companion",
            "**Exception",
        )

        mapEnum = EnumMapping.asEnum
        stringQuotes = StringQuotes.singleQuotes
        noEslintDisable = true
        noTslintDisable = true
        indentString = "  "

        outputFile = getTypeScriptOutput()
        jsonLibrary = JsonLibrary.jackson2
        outputKind = TypeScriptOutputKind.module
        outputFileType = TypeScriptFileType.implementationFile
    }
}

fun getTypeScriptOutput() = project.rootDir.resolve("main-frontend/app/shared/types.ts").toPath().toAbsolutePath().toString()

micronaut {
    runtime("jetty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("main.backend.*")
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
