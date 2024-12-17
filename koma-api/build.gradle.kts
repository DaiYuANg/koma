plugins {
  alias(libs.plugins.lombok)
}

group = "org.koma.api"
version = "1.0-SNAPSHOT"

dependencies {
  compileOnly(libs.jetbrains.annotation)
  implementation(libs.eclipse.collections.api)
  implementation(libs.eclipse.collections)
  api(libs.jsoup)
  api(libs.ph.css)
  implementation(libs.record.builder.core)
  annotationProcessor(libs.record.builder.processor)
}
