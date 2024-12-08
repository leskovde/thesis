rootProject.name = "auto-debugger"
include("instrumentor-common")
include("instrumentor-java")
include("model-java")
include("test-generator-common")
include("test-generator-java")
include("runner")
include("analyzer-disl")

dependencyResolutionManagement {

    versionCatalogs {
        create("libs") {
            library("log4j", "org.apache.logging.log4j:log4j-slf4j2-impl:2.23.1")
            library("javatuples", "org.javatuples:javatuples:1.2")
            library("junit5", "org.junit.jupiter:junit-jupiter:5.10.2")
            library("junit5-bom", "org.junit:junit-bom:5.10.2")
        }
    }
}
