plugins {
    id("java")
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.7.6")
    implementation(project(mapOf("path" to ":analyzer")))
    implementation(project(mapOf("path" to ":instrumentor-common")))
    implementation(project(mapOf("path" to ":instrumentor-java")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}