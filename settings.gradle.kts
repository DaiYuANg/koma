pluginManagement {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
  }
}
plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
  id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.2"
  id("com.gradle.enterprise") version "3.13.4"
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

buildCache {
  local {
    isEnabled = true
    directory = File(rootProject.projectDir, ".gradle/build-cache")
  }
}
rootProject.name = "koma"
include("koma-cli")
include("koma-core")
include("integration:gradle-plugin")
include("feature:koma-deploy-github-feature")
include("feature:koma-asciidoc-feature")
include("feature:koma-markdown-feature")
include("koma-api")
