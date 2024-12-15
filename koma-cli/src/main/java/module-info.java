module org.koma.cli {
  requires static org.jetbrains.annotations;
  requires static lombok;

  requires ch.qos.logback.classic;
  requires ch.qos.logback.core;
  requires info.picocli;
  requires jakarta.inject;
  requires org.fusesource.jansi;
  requires org.slf4j;
  requires io.javalin;
  requires org.apache.commons.io;
  requires io.github.cdimascio.dotenv.java;
  requires org.koma.core;
  requires dev.dirs;
  requires com.google.common;
  requires com.google.guice;
  requires io.github.oshai.kotlinlogging;

  requires com.fasterxml.jackson.core;
  requires com.fasterxml.jackson.databind;
  requires com.fasterxml.jackson.dataformat.yaml;

  exports org.koma.cli.module;

}