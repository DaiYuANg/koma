module org.koma.shared {
  requires transitive org.jsoup;
  requires static org.jetbrains.annotations;
  requires kotlin.stdlib;
  requires org.eclipse.collections.api;
  requires  com.helger.css;

  exports org.koma.shared;
}