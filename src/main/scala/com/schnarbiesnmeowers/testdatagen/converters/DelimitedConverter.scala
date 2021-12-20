package com.schnarbiesnmeowers.testdatagen.converters
import com.schnarbiesnmeowers.testdatagen.generators.HandleSpecialCharacters
import com.schnarbiesnmeowers.testdatagen.posos.{Record, RecordsTemplate}

class DelimitedConverter(val delimiter: String,template:RecordsTemplate) extends Converter with HandleSpecialCharacters {

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
    record.fieldValues.map(rec => rec.length match {
      case 0 => "null"
      case _ => handleSpecialCharacters(rec,delimiter)
    }).mkString(delimiter)
  }

  /**
   * TODO - add this later, the convertRecords() method body should end up being:
   * (makeHeader() + convertRecordsToMasterString(records)).getBytes
   * @return
   */
  def makeHeader():String = {
    delimiter match {
      case "," => template.fields.mkString(",")+"\n"
      case "\t" => template.fields.mkString("\t")+"\n"
      case "|" => template.fields.mkString("|")+"\n"
    }
  }

}
