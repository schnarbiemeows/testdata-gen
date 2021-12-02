/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.business

import com.schnarbiesnmeowers.testdatagen.config.user.UserConfig
import com.schnarbiesnmeowers.testdatagen.converters.{DelimitedConverter, JsonConverter, SqlConverter}
import com.schnarbiesnmeowers.testdatagen.posos.{GenericRecordsTemplate, Record, RecordsTemplate}
import com.schnarbiesnmeowers.testdatagen.utilities.{DataExtractor, DateUtils, FileIO, LogUtil}

/**
 * mode for the generation of records of any kind of random data that the user specifies
 * via the UI
 * @param userConfig - user specified configuration
 */
class DataCreationProcessor(val userConfig: UserConfig) extends Mode {

  /**
   * main run method
   */
  override def run(): Array[Byte] = {
    LogUtil.msggenMasterLoggerDEBUG("inside StreamingMessagesMode");
    /**
     * our records to generate and export
     */
    val records: RecordsTemplate = DataExtractor.parseUserConfiguration(userConfig)
    // 2. validate the data
    val templateValidated: Boolean = true //new ExcelDataSheetValidator(mode,records).validate()
    templateValidated match {
      case true => {
        LogUtil.msggenMasterLoggerDEBUG("template validated")
        // 3. read in any external files for external fields
        val recordMaker: RecordMakerCoordinator = new RecordMakerCoordinator(records)
        val generatedRecords: Array[Record] = recordMaker.generateRecords()
        val result = userConfig.getFormat match {
          case "JSON" => new JsonConverter().convertRecords(generatedRecords)
          case "CSV" => new DelimitedConverter(",").convertRecords(generatedRecords)
          case "TAB" => new DelimitedConverter("\t").convertRecords(generatedRecords)
          case "PIPE" => new DelimitedConverter("|").convertRecords(generatedRecords)
          case "MYSQL" => new SqlConverter(records.outputFileFolder, records.dataTypes).convertRecords(generatedRecords)
        }
        result
      }
      case false => {
        LogUtil.msggenMasterLoggerDEBUG("template not validated")
        Array()
      }
    }
  }
}


