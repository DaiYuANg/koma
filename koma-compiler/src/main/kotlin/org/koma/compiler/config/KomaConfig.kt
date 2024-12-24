package org.koma.compiler.config

import java.nio.file.Path
import kotlin.io.path.Path

const val KOMA_CONFIG_NAME = "koma"

data class ContentConfig(
  val posts: Path,
  val pages: Path
) {

}

data class MetadataConfig(
  val author: String,
  val theme: String,
  val social: Map<String, String>,
)

data class OutputConfig(
  val directory: Path = Path("dist"),
  val clean: Boolean = false,
)

data class ServerConfig(
  val port: Int,
)

data class SiteConfig(
  val url: String,
  val title: String,
  val description: String,
  val theme: String = "bootstrap",
)

data class TemplateConfig(
  val layout: String,
  val posts: String,
  val page: String,
)

data class KomaConfig(
  val content: ContentConfig,
  val metadata: MetadataConfig,
  val output: OutputConfig,
  val server: ServerConfig,
  val site: SiteConfig,
) {

}
