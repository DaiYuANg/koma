plugins {
  `java-gradle-plugin`
  alias(libs.plugins.plugin.publish)
}

group = "org.koma.gradle.plugin"
version = "1.0-SNAPSHOT"

dependencies {
  implementation(projects.komaCore)
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}

gradlePlugin {
  val komaGradlePlugin by plugins.creating {
    id = "org.koma.gradle.plugin"
    implementationClass = "org.koma.gradle.plugin.KomaGradlePlugin"
  }
}

kotlin {
  jvmToolchain(21)
}