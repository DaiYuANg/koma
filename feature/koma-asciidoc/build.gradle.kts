import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  `java-library`
  alias(libs.plugins.javamodularity)
  alias(libs.plugins.ksp)
}

group = "org.koma"
version = "1.0-SNAPSHOT"

dependencies {
  implementation(projects.komaShared)
  implementation(libs.asciidoctorj)
  implementation(libs.asciidoctorj.diagram)
}

tasks.compileJava {
  extensions.configure<CompileModuleOptions> {
    patchModules.config = listOf("org.koma.feature.asciidoc=${sourceSets["main"].output.asPath}")
  }
}
