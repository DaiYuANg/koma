import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  alias(libs.plugins.ksp)
  alias(libs.plugins.javamodularity)
}
dependencies {
  implementation(libs.kotlin.logging.jvm)
  implementation(projects.komaApi)
  implementation(libs.thymeleaf)
  ksp(libs.kotlin.auto.service.ksp)
  implementation(libs.kotlin.auto.service.annotations)
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}


tasks.compileJava {
  extensions.configure<CompileModuleOptions> {
    patchModules.config = listOf("org.koma.core=${sourceSets["main"].output.asPath}")
  }
}
