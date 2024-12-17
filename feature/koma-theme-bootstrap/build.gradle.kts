import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  alias(libs.plugins.javamodularity)
}

group = "org.koma"

dependencies {
  implementation(libs.kotlin.logging.jvm)
  implementation(libs.apache.common.io)
  implementation(libs.webjars.locator.core)
  implementation(libs.bootstrap)
}

tasks.compileJava{
  extensions.configure<CompileModuleOptions> {
    addModules = listOf("webjars.locator.core")
    patchModules.config = listOf("org.koma.core=${sourceSets["main"].output.asPath}")
  }
  modularity.inferModulePath.set(true)
}
