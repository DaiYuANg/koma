plugins {
  application
  alias(libs.plugins.graalvm)
  alias(libs.plugins.lombok)
  alias(libs.plugins.maniftest)
  alias(libs.plugins.shadow)
  alias(libs.plugins.ksp)
}

val mainClassPath = "org.koma.cli.KomaCLIKt"

application {
  mainClass = mainClassPath
}

group = "org.koma.cli"

dependencies {
  implementation(enforcedPlatform(libs.koin.bom))
  ksp(enforcedPlatform(libs.koin.annotation.bom))
  implementation(enforcedPlatform(libs.koin.annotation.bom))
  implementation(libs.koin.core)
  implementation(libs.koin.logger.slf4j)
  implementation(libs.koin.annotation)
  ksp(libs.koin.ksp.compiler)
  compileOnly(libs.jetbrains.annotation)
  implementation(libs.dotenv)
  implementation(libs.apache.common.io)
  implementation(libs.guava)
  implementation(libs.javalin)
  implementation(libs.slf4j)
  implementation(libs.slf4j.jdk.platform.logging)
  implementation(libs.logback.core)
  implementation(libs.logback.classic)
  implementation(libs.kotlin.logging.jvm)
  implementation(libs.directories)
  implementation(libs.jansi)
  implementation(libs.clikt)
  implementation(libs.clikt.markdown)
  testImplementation(enforcedPlatform(libs.junit.bom))
  testImplementation(libs.junit.jupiter)

  implementation(libs.jackson.core)
  implementation(libs.jackson.dataformat.yaml)

  implementation(projects.komaCore)
  implementation(projects.feature.komaMarkdownFeature)
  implementation(projects.feature.komaAsciidocFeature)
  implementation(projects.feature.komaTemplateThymeleafFeature)
}

tasks.test {
  useJUnitPlatform()
}

java {
  modularity.inferModulePath.set(true)
}

graalvmNative {
  metadataRepository {
    enabled = true
  }
  toolchainDetection.set(true)
  agent {
    enableExperimentalPredefinedClasses.set(true)
    enableExperimentalUnsafeAllocationTracing.set(true)
    trackReflectionMetadata.set(true)
    metadataCopy {
      mergeWithExisting.set(true)
    }
  }
  binaries {
    named("main") {
      mainClass.set(mainClassPath)
      sharedLibrary.set(false)
      debug.set(false)
      quickBuild.set(true)
      richOutput.set(true)

      buildArgs(
        "-H:+UnlockExperimentalVMOptions",
        "-H:+ReportUnsupportedElementsAtRuntime",
        "-H:+ReportExceptionStackTraces",
        "-H:TraceClassInitialization=true",
        """
          --trace-class-initialization=org.jcodings.spi.Charsets,org.slf4j.LoggerFactory,ch.qos.logback.core.status.StatusBase,org.jcodings.spi.ISO_8859_16,ch.qos.logback.core.status.InfoStatus,ch.qos.logback.core.util.Loader,ch.qos.logback.core.CoreConstants,org.slf4j.helpers.Reporter,ch.qos.logback.classic.Level,org.slf4j.jdk.platform.logging.SLF4JPlatformLogger,ch.qos.logback.core.util.StatusPrinter2,ch.qos.logback.core.util.StatusPrinter,org.slf4j.jdk.platform.logging.SLF4JPlatformLogger${'$'}1,ch.qos.logback.classic.Logger,org.slf4j.spi.DefaultLoggingEventBuilder
        """.trimIndent(),
        """
          --initialize-at-run-time=org.slf4j.jdk.platform.logging.SLF4JSystemLoggerFinder.getLogger,ch.qos.logback.core.status.InfoStatus
        """.trimIndent()
      )
    }
  }
}

tasks.jar {
  duplicatesStrategy = DuplicatesStrategy.INCLUDE
  dependsOn(tasks.collectReachabilityMetadata)
  from(tasks.collectReachabilityMetadata)
}

