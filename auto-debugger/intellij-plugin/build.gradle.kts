plugins {
  id("java")
  id("org.jetbrains.intellij.platform") version "2.6.0"
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2025.1")

    // Add necessary plugin dependencies for compilation here, example:
    // bundledPlugin("com.intellij.java")
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
