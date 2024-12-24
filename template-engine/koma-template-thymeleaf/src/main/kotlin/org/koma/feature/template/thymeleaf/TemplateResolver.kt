package org.koma.feature.template.thymeleaf

import org.thymeleaf.IEngineConfiguration
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver
import org.thymeleaf.templateresolver.AbstractTemplateResolver
import org.thymeleaf.templateresource.FileTemplateResource
import org.thymeleaf.templateresource.ITemplateResource
import java.nio.file.FileSystem
import java.nio.file.Files
import java.nio.file.Path

class JimfsTemplateResolver(private val fileSystem: FileSystem) : AbstractConfigurableTemplateResolver() {

  private val templateDir: Path = fileSystem.getPath("/")

  override fun computeTemplateResource(
    configuration: IEngineConfiguration?,
    ownerTemplate: String?,
    template: String?,
    resourceName: String?,
    characterEncoding: String?,
    templateResolutionAttributes: MutableMap<String, Any>?
  ): ITemplateResource {
    val templatePath = templateDir.resolve("$resourceName.html")
    return FileTemplateResource(templatePath.toFile(), characterEncoding)
  }
}