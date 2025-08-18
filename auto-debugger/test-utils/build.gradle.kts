plugins {
    id("java-library")
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.junit)
    testImplementation(libs.bundles.junit)
}

tasks.test {
    useJUnitPlatform()
}

