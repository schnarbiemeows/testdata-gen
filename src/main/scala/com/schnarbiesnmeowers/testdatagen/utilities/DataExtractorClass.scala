package com.schnarbiesnmeowers.testdatagen.utilities

import com.schnarbiesnmeowers.testdatagen.config.user.UserConfig
import com.schnarbiesnmeowers.testdatagen.posos.RecordsTemplate


class DataExtractorClass {
  def parseUserConfiguration(configuration: UserConfig, template: RecordsTemplate): RecordsTemplate = {
    println("DataExtractorClass worked")
    template
  }
}
