package com.schnarbiesnmeowers.testdatagen.converters
import com.schnarbiesnmeowers.testdatagen.posos.Record

class DelimitedConverter(val delimiter: String) extends Converter {

  override def convertRecords(records: Array[Record]): Array[Byte] = {
    delimiter match {
      case "," => records.map(rec => makeDelimtedRecord(rec,",")).mkString("\n").getBytes
      case "\t" => records.map(rec => makeDelimtedRecord(rec,"\t")).mkString("\n").getBytes
      case "|" => records.map(rec => makeDelimtedRecord(rec,"|")).mkString("\n").getBytes
    }
  }

  /**
   * method that will return the rows values as a delimited String
   * @return - String
   */
  def makeDelimtedRecord(record: Record, delimiter: String):String = {
    record.fieldValues.mkString(delimiter)
  }


}
