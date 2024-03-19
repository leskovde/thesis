plugins {
    id("java")
    id("io.freefair.lombok")
}

group = "org.example"
version = "1.0-SNAPSHOT"

val DISL_PATH: String by project

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":instrumentor-common")))
    implementation(fileTree(mapOf("dir" to DISL_PATH, "include" to listOf("*.jar"))))
    implementation(libs.slf4j)
    implementation(libs.log4j)
    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.junit5)
}

tasks.test {
    useJUnitPlatform()
}