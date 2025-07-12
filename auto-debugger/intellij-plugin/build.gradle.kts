import org.jetbrains.intellij.platform.gradle.utils.intelliJPlatformResolver
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
  id("java")
  alias(libs.plugins.intelliJPlatform) // IntelliJ Platform Gradle Plugin
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

// https://platform.jetbrains.com/t/new-intellijplatformresolver-helper-to-control-the-intellij-platform-gradle-plugin-dependency-extraction/2024
val intelliJPlatformResolver = project.intelliJPlatformResolver()

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
  implementation(project(mapOf("path" to ":model-java")))
  implementation(project(mapOf("path" to ":instrumentor-common")))
  implementation(project(mapOf("path" to ":runner")))

  testImplementation(libs.bundles.junit)
  testImplementation(libs.opentest4j)
  testImplementation(libs.mockito)

  testRuntimeOnly("junit:junit:4.13.2")

  intellijPlatform {
    local(
      intelliJPlatformResolver.resolve(
        type = providers.gradleProperty("platformType"),
        version = providers.gradleProperty("platformVersion"),
      )
    )
    intellijIdeaCommunity("2025.1")
    bundledPlugin("com.intellij.java")

    testFramework(TestFrameworkType.JUnit5)
    testFramework(TestFrameworkType.Platform)
    testFramework(TestFrameworkType.Plugin.Java)

    configurations.all {
      exclude(group = "org.apache.logging.log4j", module = "log4j-slf4j2-impl")
    }
  }
}

intellijPlatform {
  buildSearchableOptions = false

  pluginConfiguration {
    ideaVersion {
      sinceBuild = "251"
    }

    changeNotes = """
      Initial version
    """.trimIndent()
  }

  pluginVerification  {
    ides {
      recommended()
    }
  }
}

tasks.test {
  useJUnitPlatform()
}
