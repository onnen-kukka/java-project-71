import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    java
    id("com.github.ben-manes.versions") version "0.53.0"
    application
    id("com.diffplug.spotless") version "6.25.0"
    jacoco
}

application {
    mainClass = "hexlet.code.App"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
}

tasks.test {
    useJUnitPlatform()
}

configure<SpotlessExtension> {
    java {
        target("src/main/java/**/*.java")
        target("src/test/java/**/*.java")
        googleJavaFormat()
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val coverageExcludes = listOf("io/hexlet/Application.class")

fun JacocoReportBase.excludeEntryPoint() {
    classDirectories.setFrom(
        files(classDirectories.files.map { fileTree(it) { exclude(coverageExcludes) } }),
    )
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    excludeEntryPoint()
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.check { dependsOn(tasks.jacocoTestCoverageVerification) }