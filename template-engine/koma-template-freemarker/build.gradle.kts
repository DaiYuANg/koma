plugins {}

group = "org.koma"

version = "1.0-SNAPSHOT"

dependencies {
  implementation(projects.komaShared)
  implementation(libs.freemarker)
  testImplementation(kotlin("test"))
}
