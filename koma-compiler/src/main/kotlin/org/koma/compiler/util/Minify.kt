package org.koma.compiler.util

import `in`.wilsonl.minifyhtml.Configuration
import `in`.wilsonl.minifyhtml.MinifyHtml

private val cfg: Configuration =
    Configuration.Builder()
        .setKeepHtmlAndHeadOpeningTags(true)
        .setMinifyCss(true)
        .setMinifyJs(true)
        .build()

fun minifyHtml(htmlSource: String): String? = MinifyHtml.minify(htmlSource, cfg)
