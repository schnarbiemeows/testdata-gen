package com.schanrbiesnmeowers.testdatagen.config.ui

import collection.JavaConverters._

case class DateTimeOps(val dateFormats: List[String] = List("yyyy-MM-dd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS")
                      ) {
  def getDateFormats() = dateFormats.asJava

}
