/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.generators

import scala.collection.mutable.ArrayBuffer

/**
  * this object generates each of the different String types
  */
object StringTypeGenerator extends Generator{

  /**
    * this method generates a random RandomString
    * @param format - formatting specifications for the field
    * @param qualifiers - array of format qualifiers
    * @return - string
    */
  def makeRandomString(format: String,qualifiers:Array[String]):String = {
    var length:Int = 10
    var min:Int = 0
    var max:Int = 0
    var chars:Option[String] = None
    var toUpper:Boolean = false
    var toLower:Boolean = false
    var nullPercentage:Double = 0.0
    val splitLists:Tuple2[Array[String],Array[String]] = filterQualifiers("RandomString", format)
    val formatsThatNeedQualifierChecks:Array[String] = splitLists._1
    val formatsNotNeedingQualifiers = splitLists._2
    for (i <- 0 until formatsThatNeedQualifierChecks.length) {
      formatsThatNeedQualifierChecks(i) match {
        case "length" => length = qualifiers(i).toInt
        case "min" => min = qualifiers(i).toInt
        case "max" => max = qualifiers(i).toInt
        case "chars" => chars = Some(qualifiers(i).toString)
        case "nullable" => nullPercentage = qualifiers(i).toDouble/100
      }
    }
    for (i <- 0 until formatsNotNeedingQualifiers.length) {
      formatsNotNeedingQualifiers(i) match {
        case "upper" => toUpper = true
        case "lower" => toLower = true
        case _ => None
      }
    }
    var result = if(chars==None) {
      if(min>0 && max>0)
        randomAlphaNumericVariableLength(min,max)
      else
        randomAlphaNumeric(length)
    } else {
      if(min>0 && max>0)
        randomAlphaNumericVariableLength(min,max,chars.get)
      else
        randomAlphaNumeric(length,chars.get)
    }
    result = if(toUpper) result.toUpperCase else if(toLower) result.toLowerCase else result
    val randomNum = randomDouble(0,1,2,"rounddown")
    if(randomNum<nullPercentage) "" else result
  }
}
