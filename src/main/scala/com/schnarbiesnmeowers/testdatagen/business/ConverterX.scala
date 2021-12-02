package com.schnarbiesnmeowers.testdatagen.business

import com.schnarbiesnmeowers.testdatagen.posos.Record

object ConverterX {

  def convert(records:Array[Record], fileType: String):Array[Byte] = {
    val convertedRecords = records.map(rec => rec.toCSV())
    val strings = convertedRecords.mkString("\n")
    val bytes = strings.getBytes
    bytes
  }

}
