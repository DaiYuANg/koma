package org.koma.cli.listener

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor
import org.apache.commons.io.monitor.FileAlterationObserver
import org.koin.core.annotation.Single

@Single
class LayoutListener : FileAlterationListenerAdaptor() {
  private val log = KotlinLogging.logger {}

  override fun onDirectoryCreate(directory: File) {
    log.atInfo { message = "onDirectoryCreate,${directory.absolutePath}" }
  }

  override fun onFileCreate(file: File) {
    log.atInfo { message = "onFileCreate,${file.absolutePath}" }
  }

  override fun onStart(observer: FileAlterationObserver) {
    log.atInfo { message = "onStart,$observer" }
  }
}
