import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType

val overrideForPlainTermEnvironment = System.getenv("TERM")?.let { it == "dumb" } ?: true

plugins {
    kotlin("jvm") version "1.9.21"
    id("com.adarshr.test-logger") version "4.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

configure<TestLoggerExtension> {
    theme = if (overrideForPlainTermEnvironment) ThemeType.PLAIN_PARALLEL else ThemeType.MOCHA_PARALLEL
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}
