package org.koma.shared.api

interface Template{
  fun parse(content: String): String
}