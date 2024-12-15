module org.koma.core {
  requires static io.soabase.recordbuilder.core;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires static java.compiler;
  requires org.apache.commons.io;
  requires org.slf4j;
  requires org.jsoup;
  requires kotlin.stdlib;
  requires org.asciidoctor.asciidoctorj;
  requires org.asciidoctor.asciidoctorj.api;

  requires com.github.jknack.handlebars;
  requires gg.jte.runtime;
  requires gg.jte;
  requires org.immutables.value;
  requires com.fasterxml.jackson.databind;
  requires flexmark.util.data;
  requires flexmark.util.ast;
  requires flexmark;
  requires thymeleaf;

  exports org.koma.core.layout;
  exports org.koma.core.config;
  exports org.koma.core.compiler;
}