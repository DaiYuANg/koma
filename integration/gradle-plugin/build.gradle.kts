plugins {
  `java-gradle-plugin`
  alias(libs.plugins.plugin.publish)
}

group = "org.koma.gradle.plugin"

version = "1.0-SNAPSHOT"

dependencies { implementation(projects.komaCompiler) }

tasks.test { useJUnitPlatform() }

gradlePlugin {
  val komaGradlePlugin by
      plugins.creating {
        id = "org.koma.gradle.plugin"
        implementationClass = "org.koma.gradle.plugin.KomaPlugin"
      }
}
