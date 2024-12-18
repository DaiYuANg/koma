import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  alias(libs.plugins.javamodularity)
}

group = "org.koma.theme.bootstrap"

dependencies {
  implementation(libs.apache.common.io)
  implementation(libs.webjars.locator.core)
  implementation(libs.bootstrap)
  implementation(projects.komaShared)
}

tasks.compileJava {
  extensions.configure<CompileModuleOptions> {
    addModules = listOf("webjars.locator.core")
    patchModules.config = listOf("org.koma.core=${sourceSets["main"].output.asPath}")
  }
  modularity.inferModulePath.set(true)
}
