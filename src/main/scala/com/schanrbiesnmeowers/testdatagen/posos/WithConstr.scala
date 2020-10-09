package com.schanrbiesnmeowers.testdatagen.posos

@SerialVersionUID(100L)
class WithConstr(val name: String, val id: Int) extends Serializable {
  def getName():String = name
  def getId():Int = id


  /*def this(name: String,id: Int) {
    this(name,id)
    println("created class with : " + name + " and " + id)
  }*/
}
