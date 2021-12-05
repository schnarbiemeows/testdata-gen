package com.schnarbiesnmeowers.testdatagen.converters
import com.schnarbiesnmeowers.testdatagen.generators.HandleSpecialCharacters
import com.schnarbiesnmeowers.testdatagen.posos.Record

class DelimitedConverter(val delimiter: String,val fieldNames: Array[String]) extends Converter with HandleSpecialCharacters {

  override def convertRecords(records: Array[Record]): Array[Byte] = {
    convertRecordsToMasterString(records).getBytes
  }

  def convertRecordsToMasterString(records: Array[Record]): String = {
    delimiter match {
      case "," => records.map(rec => makeDelimtedRecord(rec,",")).mkString("\n")
      case "\t" => records.map(rec => makeDelimtedRecord(rec,"\t")).mkString("\n")
      case "|" => records.map(rec => makeDelimtedRecord(rec,"|")).mkString("\n")
    }
  }
  /**
   * method that will return the rows values as a delimited String
   * @return - String
   */
  def makeDelimtedRecord(record: Record, delimiter: String):String = {
    record.fieldValues.map(rec => handleSpecialCharacters(rec,delimiter)).mkString(delimiter)
  }

  /**
   * TODO - add this later, the convertRecords() method body should end up being:
   * (makeHeader() + convertRecordsToMasterString(records)).getBytes
   * @return
   */
  def makeHeader():String = {
    delimiter match {
      case "," => fieldNames.mkString(",")+"\n"
      case "\t" => fieldNames.mkString("\t")+"\n"
      case "|" => fieldNames.mkString("|")+"\n"
    }
  }

}
