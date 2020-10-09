package com.schanrbiesnmeowers.testdatagen.config.ui

case class WholeNumberOps(val min: BigInt, val max: BigInt) {
  def getMin() = min
  def getMax() = max
}
