package org.koma.core.template

import lombok.extern.slf4j.Slf4j
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

class ThymeleafTemplateEngine : Engine {
  override fun parse(): String {
    val templateResolver = ClassLoaderTemplateResolver()
    templateResolver.prefix = "templates/" // 类路径下的 templates 文件夹
    templateResolver.suffix = ".html"
    //    templateResolver.setTemplateMode("HTML");
//    val templateResolver = new FileTemplateResolver();
//    templateResolver.setPrefix("templates/");
//    templateResolver.setSuffix(".html");
    return ""
  }
}
