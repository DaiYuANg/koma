import com.google.devtools.ksp.gradle.KspGradleSubplugin

plugins {
  alias(libs.plugins.ksp)
}

subprojects {
  apply<KspGradleSubplugin>()

  dependencies{
    ksp(rootProject.libs.kotlin.auto.service.ksp)
    implementation(rootProject.libs.kotlin.auto.service.annotations)
  }
}