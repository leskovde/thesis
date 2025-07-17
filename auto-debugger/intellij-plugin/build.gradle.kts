import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
  id("java")
  alias(libs.plugins.lombok)
  alias(libs.plugins.intelliJPlatform)
}

group = "cz.cuni.mff.d3s"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  implementation(project(mapOf("path" to ":model-java")))
  implementation(project(mapOf("path" to ":instrumentor-common")))
  implementation(project(mapOf("path" to ":runner")))

  testImplementation(libs.bundles.junit)
  testImplementation(libs.opentest4j)
  testImplementation(libs.mockito)

  testRuntimeOnly("junit:junit:4.13.2")

  intellijPlatform {
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
