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

include("koma-compiler")

include("koma-shared")

include("integration:gradle-plugin")

include("feature:koma-deploy-github")

include("feature:koma-asciidoc")

include("feature:koma-markdown")

include("template-engine:koma-template-thymeleaf")

include("template-engine:koma-template-freemarker")

include("feature:koma-deploy-gitlab")

include("template-engine:koma-template-handlebars")

include("template-engine:koma-template-pebble")

include("template-engine:koma-template-rocker")

include("template-engine:koma-template-jte")

include("module:koma-frontend-ecosystem")

include("theme:koma-theme-bootstrap")

findProject(":feature:koma-theme-bootstrap")?.name = "koma-theme-bootstrap"
