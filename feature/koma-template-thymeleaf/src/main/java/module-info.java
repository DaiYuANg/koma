module org.koma.thymeleaf.feature {
  requires org.koma.api;
  requires kotlin.stdlib;
  requires com.google.auto.service;
  requires thymeleaf;
  requires io.github.oshai.kotlinlogging;

  exports org.koma.feature.template.thymeleaf;
}