module org.koma.feature.markdown {
  requires org.koma.shared;
  requires org.jsoup;
  requires flexmark;
  requires kotlin.stdlib;
  requires flexmark.util.data;
  requires flexmark.util.ast;
  requires flexmark.ext.emoji;
  requires flexmark.util.sequence;
  requires com.google.auto.service;
  requires io.github.oshai.kotlinlogging;
  requires flexmark.ext.tables;
  requires flexmark.ext.gfm.strikethrough;
  requires flexmark.ext.autolink;
  requires flexmark.ext.anchorlink;
  requires flexmark.ext.aside;
  requires flexmark.ext.toc;
  requires flexmark.ext.attributes;
  requires flexmark.ext.yaml.front.matter;
  requires flexmark.ext.youtube.embedded;
  requires flexmark.profile.pegdown;
  requires flexmark.ext.resizable.image;
  requires flexmark.ext.admonition;
  requires flexmark.ext.enumerated.reference;
  requires org.apache.tika.core;

  exports org.koma.feature.markdown;
}