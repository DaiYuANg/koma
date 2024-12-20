package org.koma.compiler.util

fun outputFilename(
    filename: String,
    hashCode: Int,
): String = "$filename-$hashCode.html"
