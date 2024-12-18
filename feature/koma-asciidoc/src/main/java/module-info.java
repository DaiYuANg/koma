module org.koma.feature.asciidoc {
  requires org.asciidoctor.asciidoctorj;
  requires org.asciidoctor.asciidoctorj.api;
  requires org.koma.shared;
  requires kotlin.stdlib;
  requires com.google.auto.service;
  requires io.github.oshai.kotlinlogging;

  exports org.koma.feature.asciidoc;
}