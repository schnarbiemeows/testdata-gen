package com.schnarbiesnmeowers.testdatagen.converters
import com.schnarbiesnmeowers.testdatagen.generators.HandleSpecialCharacters
import com.schnarbiesnmeowers.testdatagen.posos.{Record, RecordsTemplate}

class SqlConverter(template:RecordsTemplate) extends Converter with HandleSpecialCharacters{

  override def convertRecords(records: Array[Record]): Array[Byte] = {
    convertRecordsToMasterString(records).getBytes
  }

  def convertRecordsToMasterString(records: Array[Record]): String = {
    records.map(rec => makeInsertStatement(rec,template.outputFileFolder)).mkString("\n")
  }

  def makeInsertStatement(record: Record, tableName: String): String = {
    val newFieldValues = record.fieldValues.zipWithIndex.map(rec => rec._1.length match {
        case 0 => "null"
        case _ => template.dataTypes(rec._2) match {
          case "RandomString" => "'" + handleSpecialCharacters(rec._1,"MYSQL") + "'"
          case "EnumDate" | "RandomDate" | "RangedDate" => {
            "STR_TO_DATE('" + rec._1 + "', '" + template.dateTimeFormats(rec._2).replaceAll("uuuu","%Y")
              .replaceAll("MMM","%M").replaceAll("dd","%d").replaceAll("MM","%m") +
              "')"
          }
          case "EnumDateTime" | "RandomDateTime" | "RangedDateTime" => {
            "STR_TO_DATE('" + rec._1 + "', '" + template.dateTimeFormats(rec._2).replaceAll("uuuu","%Y")
              .replaceAll("MMM","%M").replaceAll("dd","%d").replaceAll("MM","%m")
              .replaceAll("HH","%H").replaceAll("mm","%i").replaceAll("ss","%s") +
              "')"
          }
          case _ => rec._1
      }
    })
    "insert into " + tableName + "(" + record.fields.mkString(",") + ") values (" + newFieldValues.mkString(",") + ");"
  }
}
