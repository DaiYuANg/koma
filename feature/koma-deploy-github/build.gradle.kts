plugins {
  `java-library`
}

group = "org.koma.deploy.github"
version = "1.0-SNAPSHOT"


dependencies {
  implementation(libs.github.api)
}

tasks.test {
  useJUnitPlatform()
}
