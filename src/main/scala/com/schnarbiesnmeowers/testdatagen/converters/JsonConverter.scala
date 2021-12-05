package com.schnarbiesnmeowers.testdatagen.converters
import com.schnarbiesnmeowers.testdatagen.generators.HandleSpecialCharacters
import com.schnarbiesnmeowers.testdatagen.posos.Record

class JsonConverter(val dataTypes:Array[String]) extends Converter with HandleSpecialCharacters {
  override def convertRecords(records: Array[Record]): Array[Byte] = {
    convertRecordsToMasterString(records).getBytes
  }

  def convertRecordsToMasterString(records: Array[Record]): String = {
    ("[" + records.map(rec => toJSON(rec)).mkString(",\n") + "]")
  }

  /**
   * method that will return the fields and values as a JSON string
   * @return - String
   */
  def toJSON(record: Record):String = {
    val builder:StringBuilder = new StringBuilder().append("{ ")
    var count:Int = 0
    while(count<record.fields.length) {
      dataTypes(count) match {
        case "RandomString" | "EnumString" | "EnumDate" | "RandomDate" | "RangedDate" |
             "EnumDateTime" | "RandomDateTime" | "RangedDateTime" => {
          if(count==0) {
            builder.append("\"").append(record.fields(count)).append("\" : \"")
              .append(handleSpecialCharacters(record.fieldValues(count),"JSON")).append("\" ")
          } else {
            builder.append(", \"").append(record.fields(count)).append("\" : \"")
              .append(handleSpecialCharacters(record.fieldValues(count), "JSON")).append("\" ")
          }
        }
        case _ => {
          if(count==0) {
            builder.append("\"").append(record.fields(count)).append("\" : ")
              .append(handleNullsForJson(record.fieldValues(count))).append(" ")
          } else {
            builder.append(", \"").append(record.fields(count)).append("\" : ")
              .append(handleNullsForJson(record.fieldValues(count))).append(" ")
          }
        }
      }

      count+=1
    }
    builder.append("}").toString
  }
}
