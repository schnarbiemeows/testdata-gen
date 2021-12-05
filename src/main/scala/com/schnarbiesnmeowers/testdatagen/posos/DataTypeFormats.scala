/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.posos

/**
  * this object keeps track of all of the different types of
  * keywords that can be specified for each data type's data format
  * cell(row 3).
  */
object DataTypeFormats {

  /**
    * TODO - scaladoc this
    */


  
  val keywords:Map[String,Set[String]] = Map("EnumString" -> Set("nullable"),
    "RandomString" -> Set("length","chars","upper","lower","nullable"),
    "ExternalString" -> Set("nullable"),
    "EnumInt" -> Set("nullable"),
    "RandomInt" -> Set("length","min","max","nullable"),
    "RangedInt" -> Set("length","linbase","linadd"),
    "ExternalInt" -> Set("nullable"),
    "EnumLong" -> Set("nullable"),
    "RandomLong" -> Set("length","min","max","nullable"),
    "RangedLong" -> Set("length","linbase","linadd"),
    "ExternalLong" -> Set("nullable"),
    "EnumFloat" -> Set("nullable"),
    "RandomFloat" -> Set("length","signDigits","min","max","roundup","rounddown","roundhalf","nullable"),
    "ExternalFloat" -> Set("nullable"),
    "EnumDouble" -> Set("nullable"),
    "RandomDouble" -> Set("length","signDigits","min","max","roundup","rounddown","roundhalf","nullable"),
    "ExternalDouble" -> Set("nullable"),
    "EnumDate" -> Set("nullable"),
    "RandomDate" -> Set("start","end","format","now","nullable"),
    "ExternalDate" -> Set("nullable"),
    "EnumDateTime" -> Set("nullable"),
    "RandomDateTime" -> Set("start","end","format","now","nullable"),
    "ExternalDateTime" -> Set("nullable"),
    "EnumMoney" -> Set("nullable"),
    "RandomMoney" -> Set("min","max","roundup","rounddown","roundhalf","nullable"),
    "ExternalMoney" -> Set("nullable")
  )

  val keysThatNeedQualifiers:Map[String,Set[String]] = Map("EnumString" -> Set("nullable"),
    "RandomString" -> Set("length","min","max","chars","nullable"),
    "ExternalString" -> Set("nullable"),
    "EnumInt" -> Set("nullable"),
    "RandomInt" -> Set("length","min","max","nullable"),
    "RangedInt" -> Set("length","linbase","linadd"),
    "ExternalInt" -> Set("nullable"),
    "EnumLong" -> Set("nullable"),
    "RandomLong" -> Set("length","min","max","nullable"),
    "RangedLong" -> Set("length","linbase","linadd","nullable"),
    "ExternalLong" -> Set("nullable"),
    "EnumFloat" -> Set("nullable"),
    "RandomFloat" -> Set("length","signDigits","min","max","nullable"),
    "ExternalFloat" -> Set("nullable"),
    "EnumDouble" -> Set("nullable"),
    "RandomDouble" -> Set("length","signDigits","min","max","nullable"),
    "RangedDouble" -> Set("length","signDigits","linbase","linadd","nullable"),
    "ExternalDouble" -> Set("nullable"),
    "EnumDate" -> Set("nullable"),
    "RandomDate" -> Set("start","end","format","nullable"),
    "RangedDate" -> Set("basedate","incyr","incmth","incday","format","nullable"),
    "ExternalDate" -> Set("nullable"),
    "EnumDateTime" -> Set("nullable"),
    "RandomDateTime" -> Set("start","end","format","nullable"),
    "RangedDateTime" -> Set("basedate","incyr","incmth","incday","inchr","incmin","incsec","format","nullable"),
    "ExternalDateTime" -> Set("nullable"),
    "EnumMoney" -> Set("nullable"),
    "RandomMoney" -> Set("min","max","nullable"),
    "ExternalMoney" -> Set("nullable"),
    "RandomBoolean" -> Set("nullable")
  )


}
