package com.schanrbiesnmeowers.testdatagen.posos

@SerialVersionUID(100L)
case class SimpleCaseClass(val name:String, val id:Int) extends Serializable {
  def getName():String = name
  def getId():Int = id
}
