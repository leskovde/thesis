plugins {
    id("java-library")
    alias(libs.plugins.lombok)
    id("com.gradleup.shadow") version "8.3.5"
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(project(mapOf("path" to ":test-generator-common")))
    implementation(project(mapOf("path" to ":model-common")))
    implementation(project(mapOf("path" to ":model-java")))
    implementation(libs.log4j)
    testImplementation(libs.bundles.junit)
}

tasks.assemble {
    dependsOn(tasks.named("shadowJar"))
}

tasks.jar {
    enabled = false
}

tasks.test {
    useJUnitPlatform()
}