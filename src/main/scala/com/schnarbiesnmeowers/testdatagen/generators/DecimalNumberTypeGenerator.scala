/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.generators

import scala.collection.mutable.ArrayBuffer

object DecimalNumberTypeGenerator extends Generator {

  /**
    * this method generates a random RandomDouble
    * @param format - formatting specifications for the field
    * @param qualifiers - array of possible values
    * @return - string
    */
  def makeRandomDecimalNumber(typeName:String,format: String, qualifiers:Array[String]):String = {
    var signDigits:Int = 0
    var length:Int = 10
    var min:Double = 0
    var max:Double = 0
    var roundingType:Option[String] = None
    var nullPercentage:Double = 0.0
    val splitLists:Tuple2[Array[String],Array[String]] = filterQualifiers(typeName, format)
    val formatsThatNeedQualifierChecks:Array[String] = splitLists._1
    val formatsNotNeedingQualifiers = splitLists._2
    for (i <- 0 until formatsThatNeedQualifierChecks.length) {
      formatsThatNeedQualifierChecks(i) match {
        case "length" => length = qualifiers(i).toInt
        case "signDigits" => signDigits = qualifiers(i).toInt
        case "min" => min = qualifiers(i).toDouble
        case "max" => max = qualifiers(i).toDouble
        case "nullable" => nullPercentage = qualifiers(i).toDouble/100
      }
    }
    for (i <- 0 until formatsNotNeedingQualifiers.length) {
      formatsNotNeedingQualifiers(i) match {
        case "roundup" => roundingType = Some("roundup")
        case "rounddown" => roundingType = Some("rounddown")
        case "roundhalf" => roundingType = Some("roundhalf")
      }
    }
    val result = padNumberWithZeros(randomDouble(min,max,signDigits,roundingType.getOrElse("roundhalf")).toString,length)
    val randomNum = randomDouble(0,1,2,"rounddown")
    if(randomNum<nullPercentage) "" else result
  }

  /**
   * this method generates a random RandomDouble
   * @param format - formatting specifications for the field
   * @param qualifiers - array of possible values
   * @return - string
   */
  def makeRangedDecimalNumber(typeName:String,format: String, qualifiers:Array[String],arrayNum:Int, fileNum: Int):String = {
    var signDigits:Int = 0
    var length:Int = 10
    var base:Double = 0
    var add:Double = 0
    var roundingType:Option[String] = None
    var nullPercentage:Double = 0.0
    val splitLists:Tuple2[Array[String],Array[String]] = filterQualifiers(typeName, format)
    val formatsThatNeedQualifierChecks:Array[String] = splitLists._1
    val formatsNotNeedingQualifiers = splitLists._2
    for (i <- 0 until formatsThatNeedQualifierChecks.length) {
      formatsThatNeedQualifierChecks(i) match {
        case "length" => length = qualifiers(i).toInt
        case "signDigits" => signDigits = qualifiers(i).toInt
        case "linbase" => base = qualifiers(i).toDouble
        case "linadd" => add = qualifiers(i).toDouble
        case "nullable" => nullPercentage = qualifiers(i).toDouble/100
      }
    }
    for (i <- 0 until formatsNotNeedingQualifiers.length) {
      formatsNotNeedingQualifiers(i) match {
        case "roundup" => roundingType = Some("roundup")
        case "rounddown" => roundingType = Some("rounddown")
        case "roundhalf" => roundingType = Some("roundhalf")
      }
    }
    val result = padNumberWithZeros(calculateLinearRangedNum(base,add,arrayNum,fileNum).toString,length)
    val randomNum = randomDouble(0,1,2,"rounddown")
    if(randomNum<nullPercentage) "" else result
  }

  /**
   * ths method calculates a linear value increment(like a primary key)
   * @param linbase - the initial base value; array index 0 of file # = 0 will have this value
   * @param linadd - an amount to incrementally add
   * @param arrayNum - the index in the array for this record
   * @param fileNum - what file number we are on
   * @return
   */
  def calculateLinearRangedNum(linbase:Double,linadd:Double,arrayNum:Long,fileNum:Long):Double = {
    //val numRecordsPerFile:Long = PropertyLoader.getProperty(Configuration.MODE4_NUM_RECORDS).toLong
    //linbase+linadd*fileNum*numRecordsPerFile+linadd*arrayNum
    linbase+linadd*arrayNum
  }
}
