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
    id("dev.monosoul.jooq-docker") version "1.0.1"
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


//jooq {
//    withContainer {
//        image {
//            name = "mysql:8.0.29"
//            envVars = mapOf(
//                    "MYSQL_ROOT_PASSWORD" to "sesame",
//                    "MYSQL_DATABASE" to "sakila"
//            )
//        }
//
//        db {
//            username = "root"
//            password = "sesame"
//            name = "sakila"
//            port = 3306
//
//            jdbc {
//                schema = "jdbc:sakila"
//                driverClassName = "com.mysql.cj.jdbc.Driver"
//            }
//        }
//    }
//}

tasks {
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events(STARTED, PASSED, FAILED)
            showExceptions = true
            showStackTraces = true
            showCauses = true
            exceptionFormat = FULL
        }
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "$targetJava"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}

val jooqVersion = "3.17.2"
val flywayVersion = "9.0.0"

dependencies {
    jooqCodegen("org.flywaydb:flyway-core:9.0.1")
    jooqCodegen("mysql:mysql-connector-java:8.0.29")
    implementation (project(":utilities"))
    implementation ("org.jooq:jooq-meta:3.16.6")
    implementation ("org.jooq:jooq:3.16.6")
    implementation("org.jooq:jooq-codegen:$jooqVersion")

//    implementation("org.flywaydb:flyway-core:$flywayVersion")

    val testcontainersVersion = "1.17.3"
    implementation("org.testcontainers:jdbc:$testcontainersVersion")

    testImplementation(enforcedPlatform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.strikt:strikt-jvm:0.34.1")
    testImplementation("io.mockk:mockk:1.12.4")

}
