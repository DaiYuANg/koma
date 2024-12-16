module org.koma.core {
  requires static io.soabase.recordbuilder.core;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires static java.compiler;
  requires org.apache.commons.io;
  requires org.slf4j;
  requires transitive org.jsoup;
  requires kotlin.stdlib;
  requires org.asciidoctor.asciidoctorj;
  requires org.asciidoctor.asciidoctorj.api;
  requires io.github.oshai.kotlinlogging;
  requires webjars.locator.core;
  requires koin.core.jvm;
  requires flexmark.ext.tables;

  requires com.github.jknack.handlebars;
  requires gg.jte.runtime;
  requires gg.jte;
  requires org.immutables.value;
  requires com.fasterxml.jackson.databind;
  requires flexmark.util.data;
  requires flexmark.util.ast;
  requires flexmark;
  requires thymeleaf;

  exports org.koma.core.config;
  exports org.koma.core.compiler;
  exports org.koma.core.model;
}