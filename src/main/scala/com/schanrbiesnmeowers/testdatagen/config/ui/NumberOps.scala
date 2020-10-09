package com.schanrbiesnmeowers.testdatagen.config.ui

import collection.JavaConverters._

case class NumberOps(val min: BigDecimal, val max: BigDecimal,
                     val rounding: List[String] = "Round Up" :: "Round Down" :: "Round Half Up" :: Nil) {
  def getMin() = min
  def getMax() = max
  def getRounding() = rounding.asJava
}
