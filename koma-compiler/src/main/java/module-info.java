module org.koma.compiler {
  requires static lombok;
  requires org.apache.commons.io;
  requires org.slf4j;
  requires io.github.oshai.kotlinlogging;
  requires com.google.common;
  requires minify.html;
  requires io.smallrye.mutiny;

  requires com.fasterxml.jackson.databind;
  requires org.apache.commons.collections4;
  requires org.koma.shared;
  requires kotlin.stdlib;

  exports org.koma.core.config;
  exports org.koma.core.compiler;
  exports org.koma.core.model;

}