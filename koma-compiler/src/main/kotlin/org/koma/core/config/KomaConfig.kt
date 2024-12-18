package org.koma.core.config

import java.nio.file.Path

const val FILE_NAME = "koma.yaml"

data class ContentConfig(
  val posts: Path
)

data class MetadataConfig(
  val author: String,
  val theme: String,
  val social: Map<String, String>
)

data class OutputConfig(
  val directory: String = "dist",
  val clean: Boolean = false
)

data class ServerConfig(
  val port: Int,
)

data class SiteConfig(
  val url: String,
  val title: String,
  val description: String
)

data class TemplateConfig(
  val layout: String,
  val posts: String,
  val page: String
)

data class KomaConfig(
  val content: ContentConfig,
  val metadata: MetadataConfig,
  val output: OutputConfig,
  val server: ServerConfig,
  val site: SiteConfig
)