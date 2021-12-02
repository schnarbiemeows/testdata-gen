package com.schnarbiesnmeowers.testdatagen.converters
import com.schnarbiesnmeowers.testdatagen.posos.Record

class JsonConverter extends Converter {
  override def convertRecords(records: Array[Record]): Array[Byte] = {
    ("[" + records.map(rec => toJSON(rec)).mkString(",\n") + "]").getBytes
  }

  /**
   * method that will return the fields and values as a JSON string
   * @return - String
   */
  def toJSON(record: Record):String = {
    val builder:StringBuilder = new StringBuilder().append("{ ")
    var count:Int = 0
    while(count<record.fields.length) {
      if(count==0) {
        builder.append("\"").append(record.fields(count)).append("\" : \"").append(record.fieldValues(count)).append("\" ")
      } else {
        builder.append(", \"").append(record.fields(count)).append("\" : \"").append(record.fieldValues(count)).append("\" ")
      }
      count+=1
    }
    builder.append("}").toString
  }
}
