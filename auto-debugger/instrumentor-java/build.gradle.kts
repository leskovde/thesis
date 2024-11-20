plugins {
    id("java")
    id("io.freefair.lombok")
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
    testImplementation(platform(libs.junit5.bom))
    testImplementation(libs.junit5)
}

tasks.test {
    useJUnitPlatform()
}
