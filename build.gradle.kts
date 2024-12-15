import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

plugins {
  alias(libs.plugins.version.check)
  alias(libs.plugins.semver)
  alias(libs.plugins.spotless)
  alias(libs.plugins.dotenv)
  alias(libs.plugins.kotlin) apply false
}

group = "org.koma"
version = "1.0-SNAPSHOT"

allprojects {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
  }
}

subprojects {
  apply<KotlinPluginWrapper>()

  project.extensions.getByType(KotlinJvmProjectExtension::class.java).apply {
    jvmToolchain(21)
  }
}