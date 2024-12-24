package org.koma.cli.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.toml.TomlFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.common.base.Preconditions
import com.google.common.base.Preconditions.checkArgument
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koma.compiler.config.KomaConfig
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.lang.IllegalArgumentException

@Single
class ConfigParser : KoinComponent {

  private val tomlMapper by lazy {
    configureObjectMapper(ObjectMapper(TomlFactory()))
  }

  private val jsonMapper by lazy {
    configureObjectMapper(ObjectMapper())
  }

  private val yamlMapper by lazy {
    configureObjectMapper(ObjectMapper(YAMLFactory()))
  }

  private val parsers: Map<String, ObjectMapper> by lazy {
    mapOf(
      "toml" to tomlMapper,
      "json" to jsonMapper,
      "yaml" to yamlMapper
    )
  }

  fun parse(inputStream: InputStream, extension: String): KomaConfig {
    checkArgument(inputStream.available() != 0, "InputStream is empty. Cannot parse configuration.")
    val normalizedExtension = extension.lowercase()
    checkArgument(parsers.containsKey(normalizedExtension), "Unsupported file extension: $extension")
    val mapper = parsers[normalizedExtension]
    return mapper!!.readValue(inputStream, KomaConfig::class.java)
  }

  private fun configureObjectMapper(mapper: ObjectMapper): ObjectMapper {
    return mapper.registerKotlinModule()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  }
}