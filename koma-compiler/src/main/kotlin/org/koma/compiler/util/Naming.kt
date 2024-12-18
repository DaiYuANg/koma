package org.koma.compiler.util

fun outputFilename(filename: String, hashCode: Int): String {
  return "${filename}-${hashCode}.html"
}