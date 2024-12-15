import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  `java-library`
  alias(libs.plugins.lombok)
  alias(libs.plugins.javamodularity)
}

group = "org.koma.core"

dependencies {
  implementation(libs.handlebars)
  implementation(libs.slf4j)
  implementation(libs.record.builder.core)
  implementation(libs.freemarker)
  implementation(libs.thymeleaf)
  implementation(libs.pebble)
  implementation(libs.rocker)
  implementation(libs.jte)
  implementation(libs.flexmark)
  implementation(libs.jsoup)
  implementation(libs.jackson.databind)
  implementation(libs.apache.common.io)
  compileOnly(libs.immutables.value)
  implementation(libs.asciidoctorj)
  annotationProcessor(libs.immutables.value)
  annotationProcessor(libs.record.builder.processor)
  testImplementation(platform(libs.junit.bom))
  testImplementation(libs.junit.jupiter)
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
    addModules = listOf("flexmark.util.data", "flexmark")
    patchModules.config = listOf("org.koma.core=${sourceSets["main"].output.asPath}")
  }
  modularity.inferModulePath.set(true)
  println(options)
}