plugins {
    id("java")
    id("io.freefair.lombok") version "8.4"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":runner")))
    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.junit5)
}

tasks.test {
    useJUnitPlatform()
}