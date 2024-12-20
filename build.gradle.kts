import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
  alias(libs.plugins.version.check)
  alias(libs.plugins.semver)
  alias(libs.plugins.spotless)
  alias(libs.plugins.dotenv)
  `java-library`
  alias(libs.plugins.kotlin) apply false
}

group = "org.koma"

version = "1.0-SNAPSHOT"

allprojects {
  repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
    gradlePluginPortal()
    google()
  }
}

subprojects {
  apply(plugin = "org.jetbrains.kotlin.jvm")
  apply<JavaLibraryPlugin>()

  project.extensions.getByType(KotlinJvmProjectExtension::class.java).apply { jvmToolchain(21) }
  project.dependencies {
    implementation(rootProject.libs.kotlin.logging.jvm)
    testImplementation(kotlin("test"))
  }
  project.tasks.test { useJUnitPlatform() }
}

spotless {
  kotlin {
    target("**/*.kt")
    ktfmt()
  }
  kotlinGradle {
    target("**/*.gradle.kts")
    ktfmt()
  }
}
