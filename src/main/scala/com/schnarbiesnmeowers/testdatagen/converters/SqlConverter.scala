package com.schnarbiesnmeowers.testdatagen.converters
import com.schnarbiesnmeowers.testdatagen.generators.HandleSpecialCharacters
import com.schnarbiesnmeowers.testdatagen.posos.Record

class SqlConverter(tableName: String, dataTypes: Array[String]) extends Converter with HandleSpecialCharacters{

  override def convertRecords(records: Array[Record]): Array[Byte] = {
    convertRecordsToMasterString(records).getBytes
  }

  def convertRecordsToMasterString(records: Array[Record]): String = {
    records.map(rec => makeInsertStatement(rec,tableName)).mkString("\n")
  }

  def makeInsertStatement(record: Record, tableName: String): String = {
    val newFieldValues = record.fieldValues.zipWithIndex.map(rec => dataTypes(rec._2) match {
      case "RandomString" => "'" + handleSpecialCharacters(rec._1,"MYSQL") + "'"
      case _ => rec._1.length match {
        case 0 => "null"
        case _ => rec._1
      }
    })
    "insert into " + tableName + "(" + record.fields.mkString(",") + ") values (" + newFieldValues.mkString(",") + ");"
  }
}
