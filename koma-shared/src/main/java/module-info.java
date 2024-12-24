module org.koma.shared {
  requires transitive org.jsoup;
  requires static org.jetbrains.annotations;
  requires kotlin.stdlib;
  requires org.eclipse.collections.api;
  requires com.helger.css;
  requires org.apache.commons.codec;
  requires com.google.common.jimfs;

  exports org.koma.shared.api;
  exports org.koma.shared.context;
  exports org.koma.shared.data.structure;
  exports org.koma.shared.data;
}