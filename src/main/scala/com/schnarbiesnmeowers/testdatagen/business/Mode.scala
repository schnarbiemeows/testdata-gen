/*
 * Created 2021 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.business

/**
  * interface for mode of operation of the program
  */
trait Mode {

  /**
    * main run method
    */
  def run(): Array[Byte]
}
