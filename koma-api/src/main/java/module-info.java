module org.koma.api {
  requires transitive org.jsoup;
  requires static org.jetbrains.annotations;
  requires kotlin.stdlib;
  requires static java.compiler;
  requires io.soabase.recordbuilder.core;

  exports org.koma.api;
}