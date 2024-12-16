@file:JvmName("KomaCompiler")

package org.koma.core.compiler

import org.asciidoctor.Asciidoctor
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koma.core.config.KomaConfig
import org.koma.core.context.ParseContext
import org.koma.core.model.KomaLayout
import org.koma.core.vistor.CompileSourceVisitor
import java.nio.file.Files
import java.util.concurrent.CompletableFuture

val coreModule = module {
  single { Asciidoctor.Factory.create() }
}

fun compile(config: KomaConfig, layout: KomaLayout) {
  startKoin {
    modules(coreModule)
  }
  val postsPath = layout.path.resolve(config.content().posts())
  val pagesPath = layout.path.resolve(config.content().pages())
  val postContext = ParseContext()
  val pagesContext = ParseContext()
  val a = CompletableFuture.runAsync {
    Files.walkFileTree(postsPath, CompileSourceVisitor(postContext))
  }

  val b = CompletableFuture.runAsync {
    Files.walkFileTree(pagesPath, CompileSourceVisitor(pagesContext))
  }

  CompletableFuture.allOf(a, b).join()
}