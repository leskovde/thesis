plugins {
    id("java")
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