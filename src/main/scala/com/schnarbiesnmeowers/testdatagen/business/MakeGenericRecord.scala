/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.business

import com.schnarbiesnmeowers.testdatagen.generators.{BooleanTypeGenerator, DateTimeTypeGenerator, DateTypeGenerator, DecimalNumberTypeGenerator, MoneyTypeGenerator, StringTypeGenerator, WholeNumberTypeGenerator}
import com.schnarbiesnmeowers.testdatagen.posos.{GenericRecord, Record, RecordsTemplate}
import com.schnarbiesnmeowers.testdatagen.utilities.LogUtil

/**
  * this class is for making Record objects
  */
object MakeGenericRecord {

  /**
    * this method makes a Generic record from the meta-data contained in a
    * RecordsTemplate object
    * @param records - template that contains the meta-data for making our record
    * @param arrayNum - the array number in the Array where the record will go
    * @param fileNum - the current file numbeer that we are working on
    * @return - Record = our new record
    */
  def makeRecord(records: RecordsTemplate, arrayNum: Int, fileNum: Int):Record = {
    val fields = records.fields
    var fieldValues = new Array[String](fields.length)
    val dataTypes = records.dataTypes
    val formats = records.dataFormats
    val allQualifiers = records.dataQualifiers
    for(i <- 0 until fields.length) {
      val field = fields(i)
      val dataType = dataTypes(i)
      val format = formats(i)
      val qualifiers = allQualifiers(i)
      // TODO - make this smaller by using case in List(<types>)
      // TODO - incremental date and date time is not included here!
      dataType match {
        case "EnumString" => fieldValues(i) = StringTypeGenerator.makeRandonizedExternalOrEnumDataType("EnumString", format, qualifiers)
        case "RandomString" => fieldValues(i) = StringTypeGenerator.makeRandomString(format, qualifiers)
        case "ExternalString" => fieldValues(i) = StringTypeGenerator.makeRandonizedExternalOrEnumDataType("ExternalString", format, qualifiers)
        case "EnumInt" => fieldValues(i) = WholeNumberTypeGenerator.makeRandonizedExternalOrEnumDataType("EnumInt", format, qualifiers)
        case "RandomInt" => fieldValues(i) = WholeNumberTypeGenerator.makeRandomWholeNumber(format,qualifiers)
        case "RangedInt" => fieldValues(i) = WholeNumberTypeGenerator.makeRangedWholeNumber("RangedInt",format,qualifiers,arrayNum,fileNum)
        case "ExternalInt" => fieldValues(i) = WholeNumberTypeGenerator.makeRandonizedExternalOrEnumDataType("ExternalInt", format, qualifiers)
        case "EnumLong" => fieldValues(i) = WholeNumberTypeGenerator.makeRandonizedExternalOrEnumDataType("EnumLong", format, qualifiers)
        case "RandomLong" => fieldValues(i) = WholeNumberTypeGenerator.makeRandomWholeNumber(format,qualifiers)
        case "RangedLong" => fieldValues(i) = WholeNumberTypeGenerator.makeRangedWholeNumber("RangedLong",format,qualifiers,arrayNum,fileNum)
        case "ExternalLong" => fieldValues(i) = WholeNumberTypeGenerator.makeRandonizedExternalOrEnumDataType("ExternalLong", format, qualifiers)
        case "EnumFloat" => fieldValues(i) = DecimalNumberTypeGenerator.makeRandonizedExternalOrEnumDataType("EnumFloat", format, qualifiers)
        case "RandomFloat" => fieldValues(i) = DecimalNumberTypeGenerator.makeRandomDecimalNumber("RandomDouble",format,qualifiers)
        case "ExternalFloat" => fieldValues(i) = DecimalNumberTypeGenerator.makeRandonizedExternalOrEnumDataType("ExternalFloat", format, qualifiers)
        case "EnumDouble" => fieldValues(i) = DecimalNumberTypeGenerator.makeRandonizedExternalOrEnumDataType("EnumDouble", format, qualifiers)
        case "RandomDouble" => fieldValues(i) = DecimalNumberTypeGenerator.makeRandomDecimalNumber("RandomDouble",format,qualifiers)
        case "RangedDouble" => fieldValues(i) = DecimalNumberTypeGenerator.makeRangedDecimalNumber("RangedDouble",format,qualifiers,arrayNum,fileNum)
        case "ExternalDouble" => fieldValues(i) = DecimalNumberTypeGenerator.makeRandonizedExternalOrEnumDataType("ExternalDouble", format, qualifiers)
        case "EnumDate" => fieldValues(i) = DateTypeGenerator.makeRandonizedExternalOrEnumDataType("EnumDate", format, qualifiers)
        case "RandomDate" => fieldValues(i) = DateTypeGenerator.makeRandomDate(format,qualifiers)
        case "ExternalDate" => fieldValues(i) = DateTypeGenerator.makeRandonizedExternalOrEnumDataType("ExternalDate", format, qualifiers)
        case "EnumDateTime" => fieldValues(i) = DateTimeTypeGenerator.makeRandonizedExternalOrEnumDataType("EnumDateTime", format, qualifiers)
        case "RandomDateTime" => fieldValues(i) = DateTimeTypeGenerator.makeRandomDateTime(format,qualifiers)
        case "ExternalDateTime" => fieldValues(i) = DateTimeTypeGenerator.makeRandonizedExternalOrEnumDataType("ExternalDateTime", format, qualifiers)
        case "EnumMoney" => fieldValues(i) = MoneyTypeGenerator.makeRandonizedExternalOrEnumDataType("EnumMoney", format, qualifiers)
        case "RandomMoney" => fieldValues(i) = MoneyTypeGenerator.makeRandomMoney(format,qualifiers)
        case "ExternalMoney" => fieldValues(i) = MoneyTypeGenerator.makeRandonizedExternalOrEnumDataType("ExternalMoney", format, qualifiers)
        case "RandomBoolean" => fieldValues(i) = BooleanTypeGenerator.makeRandomBoolean(format,qualifiers)
        case _ => LogUtil.msggenMasterLoggerDEBUG("Data Type not found")
      }
    }
    new GenericRecord(fields,fieldValues)
  }
}
