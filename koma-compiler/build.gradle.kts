import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  `java-library`
  alias(libs.plugins.javamodularity)
}

group = "org.koma.compiler"

dependencies {
  compileOnly(libs.jetbrains.annotation)
  implementation(libs.slf4j)
  implementation(libs.jackson.databind)
  implementation(libs.apache.common.io)
  testImplementation(platform(libs.junit.bom))
  testImplementation(libs.junit.jupiter)
  implementation(libs.kotlin.logging.jvm)
  implementation(libs.guava)
  implementation(libs.minify.html)
  implementation(libs.mutiny)
  implementation(libs.apache.common.collection)
  implementation(libs.kotlinx.html.jvm)
  implementation(libs.kotlinx.html)
  implementation(projects.komaShared)
  implementation(libs.apache.common.codec)
  implementation("org.webjars.npm:tailwindcss:4.0.0-beta.3")
}

java { modularity.inferModulePath.set(true) }

tasks.compileJava {
  extensions.configure<CompileModuleOptions> {
    patchModules.config = listOf("org.koma.core=${sourceSets["main"].output.asPath}")
  }
  modularity.inferModulePath.set(true)
  println(options)
}

tasks.jar { duplicatesStrategy = DuplicatesStrategy.INCLUDE }
