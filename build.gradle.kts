import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

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

  project.extensions.getByType(KotlinJvmProjectExtension::class.java).apply {
    jvmToolchain(21)
  }
  project.tasks.test {
    useJUnitPlatform()
  }
}

