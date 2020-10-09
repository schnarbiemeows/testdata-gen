package com.schanrbiesnmeowers.testdatagen.config.ui

case class StringOps(val minlength: Int = 1,val maxlength: Int = 256) {

  val lowers: String = "abcdefghijklmnopqrstuvwxyz"
  val uppers: String = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  val numbers: String = "0123456789"
  val specials: String = "!@#$%^&*()-_+=[]{}|,.<>/?;:~`"
  def getMinlength() = minlength
  def getMaxlength() = maxlength
  def getLowers() = lowers
  def getUppers() = uppers
  def getNumbers() = numbers
  def getSpecials() = specials

}
