group = "org.koma.feature.markdown"
version = "1.0-SNAPSHOT"

dependencies {
  compileOnly(projects.komaApi)
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}