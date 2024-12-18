package org.koma.shared

interface Engine{
  fun parse(content: String): String
}