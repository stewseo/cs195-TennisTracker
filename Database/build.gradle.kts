import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STARTED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins{
    kotlin("jvm") version "1.7.10"
    id("idea")
    id("cs195-TennisTracker.java-conventions")
    id("java-library")
    id("application")
    id("com.gradle.plugin-publish") version "0.12.0"
    id("com.palantir.docker") version "0.34.0"
}

val targetJava = JavaVersion.VERSION_17

java {
    sourceCompatibility = targetJava
    targetCompatibility = targetJava
}

repositories {
    mavenCentral()
}

application {
    mainModule.set("com.example.Database")
}

val flywayVersion = "9.0.0"

dependencies {
    implementation (project(":utilities"))
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    val testcontainersVersion = "1.17.3"
    implementation("org.testcontainers:jdbc:$testcontainersVersion")
    testImplementation("io.mockk:mockk:1.12.4")
}
