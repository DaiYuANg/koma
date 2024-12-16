plugins {
}

group = "org.koma"
version = "1.0-SNAPSHOT"



dependencies {
  implementation(libs.gitlab.api)
  testImplementation(kotlin("test"))
}
