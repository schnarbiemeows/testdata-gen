/*
 * Created 2019 by Dylan Kessler
 */

/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.generators

import scala.collection.mutable.ArrayBuffer

/**
  * class for generating random LocalDate strings
  */
object DateTypeGenerator extends Generator {

  /**
    * this method generates a random RandomDate
    * @param formatString - formatting specifications for the field
    * @param qualifiers - array of possible values
    * @return - string
    */
  def makeRandomDate(dataType: String,formatString: String, qualifiers:Array[String]):String = {
    var endWasSpecified:Boolean = false
    var startWasSpecified:Boolean = false
    var nowSpecified:Boolean = false
    var format:Option[String] = None
    var start:Option[String] = None
    var end:Option[String] = None
    var result:Option[String] = None
    var nullPercentage:Double = 0.0
    val splitLists:Tuple2[Array[String],Array[String]] = filterQualifiers(dataType, formatString)
    val formatsThatNeedQualifierChecks:Array[String] = splitLists._1
    val formatsNotNeedingQualifiers:Array[String] = splitLists._2
    for (i <- 0 until formatsNotNeedingQualifiers.length) {
      formatsNotNeedingQualifiers(i) match {
        case "now" => nowSpecified = true
        case _ => None
      }
    }
    for (i <- 0 until formatsThatNeedQualifierChecks.length) {
      formatsThatNeedQualifierChecks(i) match {
        case "format" => {
          format = Some(qualifiers(i))
        }
        case "start" => {
          startWasSpecified = true
          start = Some(qualifiers(i))
        }
        case "end" => {
          endWasSpecified = true
          end = Some(qualifiers(i))
        }
        case "nullable" => nullPercentage = qualifiers(i).toDouble/100
      }
    }
    if(nowSpecified) {
      if(format!=None) {
        result = Some(generateNowDate(format.get))
      } else {
        result = Some(generateNowDate())
      }
    }
    else {
      if(startWasSpecified&&endWasSpecified) {
        result = Some(randomDate(start.get,end.get,format.get))
      } else {
        if(startWasSpecified) {
          result = Some(randomDateNoEnd(start.get,format.get))
        } else if(endWasSpecified) {
          result = Some(randomDateNoStart(end.get,format.get))
        } else {
          result = Some(randomDate(format.get))
        }
      }
    }
    val randomNum = randomDouble(0,1,2,"rounddown")
    if(randomNum<nullPercentage) "" else result.getOrElse("")
  }

  /**
   * this method generates an incrementing date, so each record in the array will have a newer date than the one before
   * it, and the increments between these dates is the same; this field will be chronological
   * @param formatString - formatting specifications for the field
   * @param qualifiers - array of possible values
   * @return - string
   */
  def makeRangedDate(dataType: String, formatString: String, qualifiers:Array[String],arrayNum:Int, fileNum: Int):String = {
    var format:String = ""
    var base:String = ""
    var years:Int = 0
    var months:Int = 0
    var days:Int = 0
    var nullPercentage:Double = 0.0
    val splitLists:Tuple2[Array[String],Array[String]] = filterQualifiers(dataType, formatString)
    val formatsThatNeedQualifierChecks:Array[String] = splitLists._1
    val formatsNotNeedingQualifiers:Array[String] = splitLists._2
    for (i <- 0 until formatsThatNeedQualifierChecks.length) {
      formatsThatNeedQualifierChecks(i) match {
        case "format" => format = qualifiers(i)
        case "basedate" => base = qualifiers(i)
        case "incyr" => years = qualifiers(i).toInt
        case "incmth" => months = qualifiers(i).toInt
        case "incday" => days = qualifiers(i).toInt
        case "nullable" => nullPercentage = qualifiers(i).toDouble/100
      }
    }
    val result = calculateLinearRangedDate(base,format,years,months,days,arrayNum,fileNum)
    val randomNum = randomDouble(0,1,2,"rounddown")
    if(randomNum<nullPercentage) "" else result
  }


}
