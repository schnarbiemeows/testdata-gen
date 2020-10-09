package com.schanrbiesnmeowers.testdatagen.posos

import java.time.LocalDateTime

case class ExceptionResponse(val timestamp: LocalDateTime, val message: String, val details: String) {
  def getTimestamp() = timestamp
  def getMessage() = message
  def getDetails() = details
}
