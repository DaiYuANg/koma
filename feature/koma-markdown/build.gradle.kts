import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins {
  alias(libs.plugins.javamodularity)
}

group = "org.koma.feature.markdown"
version = "1.0-SNAPSHOT"

dependencies {
  implementation(libs.tika.core)
  implementation(libs.tika.parsers)
  compileOnly(projects.komaShared)
  implementation(libs.flexmark)
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
