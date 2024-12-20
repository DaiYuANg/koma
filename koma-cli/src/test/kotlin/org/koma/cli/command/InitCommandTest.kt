package org.koma.cli.command

import com.github.ajalt.clikt.testing.test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InitCommandTest {

  @Test
  fun run() {
    val command = InitCommand()
    val result = command.test("--name Foo")
    System.err.println(result)
  }
}
