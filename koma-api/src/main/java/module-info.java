module org.koma.api {
  requires transitive org.jsoup;
  requires static org.jetbrains.annotations;
  requires kotlin.stdlib;
  requires org.eclipse.collections.api;
  requires static java.compiler;
  requires io.soabase.recordbuilder.core;
  requires com.helger.css;

  exports org.koma.api;
}