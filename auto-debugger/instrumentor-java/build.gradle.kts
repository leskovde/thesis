plugins {
    id("java")
    id("io.freefair.lombok")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":instrumentor-common")))
    implementation(libs.log4j)
    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.junit5)
}

tasks.test {
    useJUnitPlatform()
}
