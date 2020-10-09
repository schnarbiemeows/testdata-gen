package com.schanrbiesnmeowers.testdatagen.posos

class VarPoso(var name: String, var id: Int) {
  def getName():String = name
  def getId():Int = id
  def setName(thisname: String): Unit = {
    this.name = thisname
  }
  def setId(thisId: Int) : Unit = {
    this.id = thisId
  }
}
