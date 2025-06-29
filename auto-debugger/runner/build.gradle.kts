plugins {
    id("java")
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.picocli)
    implementation(project(mapOf("path" to ":model-java")))
    implementation(project(mapOf("path" to ":instrumentor-common")))
    implementation(project(mapOf("path" to ":instrumentor-java")))
    testImplementation(libs.bundles.junit)
}

tasks.test {
    useJUnitPlatform()
}