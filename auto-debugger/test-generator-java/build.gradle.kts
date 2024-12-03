plugins {
    id("java-library")
    id("io.freefair.lombok")
    id("com.gradleup.shadow") version "8.3.5"
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(project(mapOf("path" to ":test-generator-common")))
    api(project(mapOf("path" to ":model-java")))
    implementation(libs.log4j)
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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