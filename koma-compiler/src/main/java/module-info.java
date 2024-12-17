module org.koma.core {
  requires static io.soabase.recordbuilder.core;
  requires static lombok;
  requires static java.compiler;
  requires static org.jetbrains.annotations;
  requires org.apache.commons.io;
  requires org.slf4j;
  requires io.github.oshai.kotlinlogging;
  requires com.google.common;
  requires minify.html;
  requires io.smallrye.mutiny;
//  requires maven.embedder;

  requires org.immutables.value;
  requires com.fasterxml.jackson.databind;
  requires kotlin.stdlib;
  requires org.koma.api;
  requires org.apache.commons.collections4;

  exports org.koma.core.config;
  exports org.koma.core.compiler;
  exports org.koma.core.model;

}