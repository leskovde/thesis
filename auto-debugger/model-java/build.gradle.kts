plugins {
    id("java-library")
    alias(libs.plugins.lombok)
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.bundles.junit)
}

tasks.test {
    useJUnitPlatform()
}