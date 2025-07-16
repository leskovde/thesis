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
    api(project(mapOf("path" to ":model-common")))
    implementation(libs.log4j)
    testImplementation(libs.bundles.junit)
}

tasks.test {
    useJUnitPlatform()
}