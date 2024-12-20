import org.javamodularity.moduleplugin.extensions.CompileModuleOptions

plugins { alias(libs.plugins.javamodularity) }

group = "org.koma.shared"

version = "1.0-SNAPSHOT"

dependencies {
  compileOnly(libs.jetbrains.annotation)
  implementation(libs.eclipse.collections.api)
  implementation(libs.eclipse.collections)
  api(libs.jsoup)
  api(libs.ph.css)
}

tasks.compileJava {
  extensions.configure<CompileModuleOptions> {
    patchModules.config = listOf("org.koma.shared=${sourceSets["main"].output.asPath}")
  }
  modularity.inferModulePath.set(true)
}
