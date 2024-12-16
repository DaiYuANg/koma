package org.koma.core.util

fun outputFilename(filename: String, hashCode: Int): String {
  return "${filename}-${hashCode}.html"
}