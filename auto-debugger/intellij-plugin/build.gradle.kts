import org.jetbrains.intellij.platform.gradle.utils.intelliJPlatformResolver

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
  testImplementation(libs.bundles.junit)
  testImplementation(libs.opentest4j)
  testImplementation(libs.mockito)

  intellijPlatform {
    local(
      intelliJPlatformResolver.resolve(
        type = providers.gradleProperty("platformType"),
        version = providers.gradleProperty("platformVersion"),
      )
    )
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

tasks {
  // Set the JVM compatibility versions
  withType<JavaCompile> {
    sourceCompatibility = "21"
    targetCompatibility = "21"
  }
}
