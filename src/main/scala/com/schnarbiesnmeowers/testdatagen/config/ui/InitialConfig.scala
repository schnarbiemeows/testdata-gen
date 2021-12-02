package com.schnarbiesnmeowers.testdatagen.config.ui

import collection.JavaConverters._

class InitialConfig(val maxrecords: Int = 1000,
                   val recordtypes: List[String] = "JSON" :: "CSV" :: "| del." :: "TAB del." :: "MySQL" :: Nil,
                   val stringOps:StringOps = StringOps(1,256),
                   val wholeNumberOps: WholeNumberOps = WholeNumberOps(BigInt(-1000),BigInt(1000)),
                   val numberOps: NumberOps = NumberOps(BigDecimal(-1000),BigDecimal(1000)),
                   val dateTimeOps: DateTimeOps = DateTimeOps()
                   ) {
  def getMaxrecords() = maxrecords
  def getRecordtypes() = recordtypes.asJava
  def getStringOps() = stringOps
  def getWholeNumberOps() = wholeNumberOps
  def getNumberOps() = numberOps
  def getDateTimeOps() = dateTimeOps
}
