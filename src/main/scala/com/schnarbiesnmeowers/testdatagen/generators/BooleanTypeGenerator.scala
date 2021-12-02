package com.schnarbiesnmeowers.testdatagen.generators

import scala.collection.mutable.ArrayBuffer

object BooleanTypeGenerator {
  def makeRandomBoolean(format: String, qualifiers: Array[String]): String = {
    var nullPercentage:Double = 0.0
    val splitLists:Tuple2[Array[String],Array[String]] = filterQualifiers("RandomLong", format)
    val formatsThatNeedQualifierChecks:Array[String] = splitLists._1
    //val formatsNotNeedingQualifiers = splitLists._2
    for (i <- 0 until formatsThatNeedQualifierChecks.length) {
      formatsThatNeedQualifierChecks(i) match {
        case "nullable" => nullPercentage = qualifiers(i).toDouble/100
      }
    }
    val result = randomBoolean().toString
    val randomNum = randomDouble(0,1,2,"rounddown")
    if(randomNum<nullPercentage) "" else result
  }

}
