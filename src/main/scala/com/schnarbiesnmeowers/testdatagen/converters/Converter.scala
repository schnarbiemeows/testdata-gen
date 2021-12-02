package com.schnarbiesnmeowers.testdatagen.converters

import com.schnarbiesnmeowers.testdatagen.posos.Record
trait Converter {

  def convertRecords(records: Array[Record]): Array[Byte]

}
