package org.koma.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

const val EXTENSION_NAME = "templateExampleConfig"
const val TASK_NAME = "templateExample"

class KomaPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    // Add the 'template' extension object
    val extension = target.extensions.create(EXTENSION_NAME, KomaExtension::class.java, target)

    // Add a task that uses configuration from the extension object
    target.tasks.register(TASK_NAME, KomaBuildTask::class.java) {
      it.tag.set(extension.tag)
      it.message.set(extension.message)
      it.outputFile.set(extension.outputFile)
    }
  }
}