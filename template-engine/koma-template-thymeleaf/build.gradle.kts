import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  alias(libs.plugins.ksp)
  alias(libs.plugins.javamodularity)
}

dependencies {
  implementation(projects.komaShared)
  implementation(libs.thymeleaf)
}

tasks.test { useJUnitPlatform() }

tasks.compileJava {
  extensions.configure<CompileModuleOptions> {
    patchModules.config = listOf("org.koma.core=${sourceSets["main"].output.asPath}")
  }
}
