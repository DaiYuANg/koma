plugins {
  java
  application
  alias(libs.plugins.graalvm)
  alias(libs.plugins.lombok)
  alias(libs.plugins.maniftest)
  alias(libs.plugins.jlink)
  alias(libs.plugins.shadow)
}

val mainClassPath = "org.koma.cli.KomaCLI"
val mainModulePath = "org.koma.cli"

application {
  mainClass = mainClassPath
  mainModule = mainModulePath
}

group = "org.koma.cli"

dependencies {
  implementation(enforcedPlatform(libs.guice.bom))
  implementation(libs.guice)
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
//  implementation(libs.avaje.inject)
  implementation(libs.directories)
//  annotationProcessor(libs.avaje.inject.generator)
  implementation(projects.komaCore)
  implementation(libs.jansi)
  implementation(libs.picocli)
  annotationProcessor(libs.picocli.codegen)
  implementation(libs.picocli.shell.jline3)
  testImplementation(enforcedPlatform(libs.junit.bom))
  testImplementation(libs.junit.jupiter)

  implementation(libs.jackson.core)
  implementation(libs.jackson.dataformat.yaml)
}

tasks.test {
  useJUnitPlatform()
}

tasks.compileJava {
  options.compilerArgumentProviders.add(CommandLineArgumentProvider {
    // Provide compiled Kotlin classes to javac – needed for Java/Kotlin mixed sources to work
    listOf("--patch-module", "YOUR_MODULE_NAME=${sourceSets["main"].output.asPath}")
  })
  options.compilerArgs.addAll(listOf("-Aproject=${project.group}/${project.name}"))
}

graalvmNative {
  metadataRepository {
    enabled = true
  }
  binaries {
    named("main") {
      mainClass.set("org.koma.cli.KomaCLI")
      sharedLibrary.set(false)
      debug.set(false)
      quickBuild.set(true)
      richOutput.set(true)
      buildArgs(
        """
          --trace-class-initialization=ch.qos.logback.core.CoreConstants,ch.qos.logback.core.util.StatusPrinter2,ch.qos.logback.core.status.StatusBase,ch.qos.logback.core.status.InfoStatus,org.slf4j.jdk.platform.logging.SLF4JPlatformLogger,ch.qos.logback.core.util.StatusPrinter,ch.qos.logback.core.util.Loader,org.slf4j.spi.DefaultLoggingEventBuilder,org.slf4j.jdk.platform.logging.SLF4JPlatformLogger\${'$'}1,org.slf4j.LoggerFactory,ch.qos.logback.classic.Logger,ch.qos.logback.classic.Level,org.slf4j.helpers.Reporter
        """.trimIndent()
      )
    }
  }
}

tasks.jar {
  from(tasks.collectReachabilityMetadata)
}

jlink {
  options = listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
  enableCds()
  mainClass.set(mainClassPath)
  moduleName.set(mainModulePath)
  addExtraDependencies("jackson", "jetty", "jline")
  mergedModule {
    requires("com.fasterxml.jackson.core");
    requires("com.google.j2objc.annotations");
    requires("com.fasterxml.jackson.databind");
    requires("java.logging");
    requires("java.sql");
    requires("jdk.jdi");
    requires("java.xml");
    requires("java.rmi");
    requires("jetty.servlet.api");
    requires("java.desktop");
    requires("org.slf4j");
    requires("info.picocli");
    requires("jdk.unsupported");
    requires("org.jetbrains.annotations");
    requires("com.google.errorprone.annotations");
    requires("java.management");
    requires("jdk.attach");
    requires("org.checkerframework.checker.qual");
    requires("java.datatransfer");
    requires("java.compiler");
    requires("java.instrument");
    provides("org.jline.terminal.provider.jansi").with("org.jline.terminal.impl.jansi.JansiTerminalProvider")
    provides("org.jline.terminal.provider.jna").with("org.jline.terminal.impl.jna.JnaTerminalProvider")
    provides("org.jline.terminal.provider.ffm").with("org.jline.terminal.impl.ffm.FfmTerminalProvider")
    provides("org.jline.terminal.provider.jni").with("org.jline.terminal.impl.jni.JniTerminalProvider")
    provides("org.jline.terminal.provider.exec").with("org.jline.terminal.impl.exec.ExecTerminalProvider")
  }
}

tasks.compileKotlin {
  val javaCompile = tasks.compileJava
  destinationDirectory = javaCompile.get().destinationDirectory
}

kotlin {
  jvmToolchain(21)
}

//java {
//  modularity.inferModulePath.set(true)
//}