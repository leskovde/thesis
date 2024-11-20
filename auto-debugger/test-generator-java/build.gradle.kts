plugins {
    id("java-library")
    id("io.freefair.lombok")
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

tasks.test {
    useJUnitPlatform()
}