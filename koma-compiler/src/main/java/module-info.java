module org.koma.compiler {
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

  exports org.koma.compiler.config;
  exports org.koma.compiler.compiler;
  exports org.koma.compiler.model;

}