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
    implementation(project(mapOf("path" to ":test-runner-common")))
    implementation(project(mapOf("path" to ":model-common")))
    implementation(project(mapOf("path" to ":model-java")))
    implementation(libs.log4j)

    // JUnit Platform for test execution
    implementation("org.junit.platform:junit-platform-launcher:1.10.0")
    implementation("org.junit.platform:junit-platform-engine:1.10.0")
    implementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    // For compiling generated tests
    implementation("org.eclipse.jdt:ecj:3.35.0")

    testImplementation(libs.bundles.junit)
    testImplementation(project(mapOf("path" to ":test-generator-common")))
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
