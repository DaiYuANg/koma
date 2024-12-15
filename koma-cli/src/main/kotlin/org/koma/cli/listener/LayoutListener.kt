package org.koma.cli.listener

import io.github.oshai.kotlinlogging.KotlinLogging
import lombok.extern.slf4j.Slf4j
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor
import org.apache.commons.io.monitor.FileAlterationObserver
import java.io.File

@Slf4j
class LayoutListener : FileAlterationListenerAdaptor() {
  private val log = KotlinLogging.logger {}
  override fun onDirectoryCreate(directory: File) {
    log.atInfo { message = "onDirectoryCreate,${directory.absolutePath}" }
  }

  override fun onFileCreate(file: File) {
    log.atInfo { message = "onFileCreate,${file.absolutePath}" }
  }

  override fun onStart(observer: FileAlterationObserver) {
    log.atInfo { message = "onStart,${observer}" }
  }
}
