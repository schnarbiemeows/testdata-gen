package com.schnarbiesnmeowers.testdatagen.converters
import com.schnarbiesnmeowers.testdatagen.posos.Record

class SqlConverter(tableName: String, dataTypes: Array[String]) extends Converter {

  override def convertRecords(records: Array[Record]): Array[Byte] = {
    records.map(rec => makeInsertStatement(rec,tableName)).mkString("\n").getBytes
  }

  def makeInsertStatement(record: Record, tableName: String): String = {
    val newFieldValues = record.fieldValues.zipWithIndex.map(rec => dataTypes(rec._2) match {
      case "RandomString" => "'" + rec._1 + "'"
      case _ => rec._1
    })
    println(record.fields.mkString(","))
    println(newFieldValues.mkString(","))
    "insert into " + tableName + "(" + record.fields.mkString(",") + ") values (" + newFieldValues.mkString(",") + ");"
  }
}
