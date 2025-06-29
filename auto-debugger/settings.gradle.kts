rootProject.name = "auto-debugger"
include("instrumentor-common")
include("instrumentor-java")
include("model-java")
include("test-generator-common")
include("test-generator-java")
include("runner")
include("analyzer-disl")
include("analyzer-common")
include("analyzer-java")
include("intellij-plugin")

pluginManagement {
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        gradlePluginPortal()
    }
}

