import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  `java-library`
  alias(libs.plugins.lombok)
  alias(libs.plugins.javamodularity)
}

group = "org.koma.core"

dependencies {
  compileOnly(libs.jetbrains.annotation)
  implementation(libs.handlebars)
  implementation(libs.slf4j)
  implementation(libs.record.builder.core)
  implementation(libs.freemarker)
  implementation(libs.thymeleaf)
  implementation(libs.pebble)
  implementation(libs.rocker)
  implementation(libs.jte)

  implementation(libs.jackson.databind)
  implementation(libs.apache.common.io)
  compileOnly(libs.immutables.value)
  annotationProcessor(libs.immutables.value)
  annotationProcessor(libs.record.builder.processor)
  testImplementation(platform(libs.junit.bom))
  testImplementation(libs.junit.jupiter)
  implementation(libs.kotlin.logging.jvm)
  implementation(libs.guava)
  implementation(projects.komaApi)
  implementation(libs.minify.html)
  implementation(libs.mutiny)
  implementation("org.webjars.npm:tailwindcss:4.0.0-beta.3")
  implementation("org.webjars:bootstrap:5.3.3")
  implementation("org.webjars:webjars-locator-core:0.59")
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}

java {
  modularity.inferModulePath.set(true)
}

tasks.compileJava {
  extensions.configure<CompileModuleOptions> {
    patchModules.config = listOf("org.koma.core=${sourceSets["main"].output.asPath}")
  }
  modularity.inferModulePath.set(true)
  println(options)
}

tasks.jar {
  duplicatesStrategy = DuplicatesStrategy.INCLUDE
}