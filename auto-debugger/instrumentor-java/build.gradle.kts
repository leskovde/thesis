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
    implementation(project(mapOf("path" to ":model-java")))
    implementation(project(mapOf("path" to ":instrumentor-common")))
    implementation(libs.log4j)
    implementation(libs.javatuples)
    testImplementation(libs.bundles.junit)
}

tasks.test {
    useJUnitPlatform()
}
