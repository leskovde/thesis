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
    implementation(project(mapOf("path" to ":model-common")))
    implementation(project(mapOf("path" to ":model-java")))
    implementation(project(mapOf("path" to ":analyzer-common")))
    implementation(libs.log4j)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.mockito)
    testImplementation(project(":test-utils"))
}

tasks.test {
    useJUnitPlatform()
}