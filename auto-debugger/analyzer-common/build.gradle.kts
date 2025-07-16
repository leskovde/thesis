plugins {
    id("java-library")
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(project(mapOf("path" to ":model-java")))
    testImplementation(libs.bundles.junit)
}

tasks.test {
    useJUnitPlatform()
}