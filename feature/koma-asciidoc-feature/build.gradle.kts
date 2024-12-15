plugins {
  `java-library`
}

group = "org.koma"
version = "1.0-SNAPSHOT"

dependencies {
  implementation(libs.asciidoctorj)
}

tasks.test {
  useJUnitPlatform()
}
