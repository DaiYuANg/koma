import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  alias(libs.plugins.javamodularity)
  alias(libs.plugins.ksp)
}

group = "org.koma.feature.markdown"
version = "1.0-SNAPSHOT"

dependencies {
  compileOnly(projects.komaApi)
  implementation(libs.flexmark)
  implementation(libs.kotlin.logging.jvm)
  ksp(libs.kotlin.auto.service.ksp)
  implementation(libs.kotlin.auto.service.annotations)
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}

tasks.compileJava {
  extensions.configure<CompileModuleOptions> {
    addModules = listOf(
      "flexmark.util.data",
      "flexmark",
      "flexmark.util.ast",
      "flexmark.util.sequence"
    )
    patchModules.config = listOf("org.koma.core=${sourceSets["main"].output.asPath}")
  }
}