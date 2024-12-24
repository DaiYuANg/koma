package org.koma.compiler.theme

import com.google.common.base.Preconditions
import com.google.common.reflect.ClassPath
import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.commons.io.FileUtils
import org.koma.shared.api.KomaTheme
import java.io.File
import java.nio.file.Paths
import java.util.jar.JarFile

private val log = KotlinLogging.logger { }
fun <T : KomaTheme> locate(theme: T) {
  val classLoader = theme.javaClass.classLoader
  val themeFolder = classLoader.getResource("META-INF/${theme.name()}")?.toURI()
  Preconditions.checkArgument(themeFolder != null, "Theme folder not found")
  val folder = File(themeFolder!!)
  val files = folder.list()
  files?.forEach {
    System.err.println(it)
  }
}