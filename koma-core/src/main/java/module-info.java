module org.koma.core {
  requires static io.soabase.recordbuilder.core;
  requires static lombok;
  requires static java.compiler;
  requires static org.jetbrains.annotations;
  requires org.apache.commons.io;
  requires org.slf4j;
  requires io.github.oshai.kotlinlogging;
  requires webjars.locator.core;
  requires com.google.common;
  requires minify.html;
  requires io.smallrye.mutiny;

  requires com.github.jknack.handlebars;
  requires gg.jte.runtime;
  requires gg.jte;
  requires org.immutables.value;
  requires com.fasterxml.jackson.databind;
  requires thymeleaf;
  requires kotlin.stdlib;
  requires org.koma.api;
  requires bootstrap;

  exports org.koma.core.config;
  exports org.koma.core.compiler;
  exports org.koma.core.model;

}