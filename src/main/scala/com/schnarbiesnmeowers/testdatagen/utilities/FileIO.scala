/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.utilities

import java.io.{BufferedWriter, File, FileWriter}
import java.time.LocalTime
import com.schnarbiesnmeowers.testdatagen.posos.{Record, RecordsTemplate}

//import scala.collection.JavaConversions._
//import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.Source

/**
  * this Object handles File Input and Output
  */
object FileIO {

  val accountIdLength:Int = 11

  /**
    * method to write any list of items to a file
    * @param input - list of items
    * @param filepath - path
    * @return - success indicator
    */
  def outputAnyListToFile(input: List[Any], filepath: String): Boolean = {
    val runStart = DateUtils.nowTime()
    var runStartLocal = DateUtils.nowTime()
    var runEndLocal:Tuple2[Long, LocalTime] = (0,runStartLocal)
    LogUtil.msggenMasterLoggerDEBUG("writing List to a file")
    LogUtil.msggenMasterLoggerDEBUG("file path : " + filepath)
    var outfile:BufferedWriter = null
    try {
      outfile = new BufferedWriter(new FileWriter(new File(filepath),false))
      runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
      LogUtil.logTime(s"opening the output file took => ${runEndLocal._1} milliseconds")
      runStartLocal = runEndLocal._2
      for(item <- input) {
        outfile.write(s"${item.toString}\n")
      }
      runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
      LogUtil.logTime(s"writing to the output file took => ${runEndLocal._1} milliseconds")
      runStartLocal = runEndLocal._2
      true
    }
    catch {
      case e: Exception => {
        LogUtil.msggenMasterLoggerDEBUG("there was an issue writing the List to a file")
        e.printStackTrace()
        false
      }
    }
    finally {
      LogUtil.msggenMasterLoggerDEBUG("closing our output file")
      outfile.close()
      runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
      LogUtil.logTime(s"closing the output file took => ${runEndLocal._1} milliseconds")
      runStartLocal = runEndLocal._2
      val runEnd = DateUtils.getDifferenceInMilliseconds(runStart)
      LogUtil.logTime(s"outputAnyListToFile() method time = ${runEnd._1} milliseconds")
    }
  }

  /**
    * method to write an array of records out to either csv files or json files
    * @param records - array of records to process
    * @param filepath - full path to the file

    */
  def writeGenericRecordToFile(records: Array[Record], filepath: String): Unit = {
    val runStart = DateUtils.nowTime()
    var runStartLocal = DateUtils.nowTime()
    var runEndLocal:Tuple2[Long, LocalTime] = (0,runStartLocal)
    LogUtil.msggenMasterLoggerDEBUG("writing generic records to a file")
    LogUtil.msggenMasterLoggerDEBUG(s"file path : ${filepath}")
    val fileType = "json" //PropertyLoader.getProperty(Configuration.MODE4_OUTPUT_FILE_TYPE).toString.toLowerCase
    LogUtil.msggenMasterLoggerDEBUG(s"file path : ${fileType}")
    var outfile:BufferedWriter = null
    try {
      LogUtil.msggenMasterLoggerDEBUG("opening BufferedWriter")
      outfile = new BufferedWriter(new FileWriter(new File(filepath+"."+fileType),true))
      runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
      LogUtil.logTime(s"opening the output file took => ${runEndLocal._1} milliseconds")
      runStartLocal = runEndLocal._2
      LogUtil.msggenMasterLoggerDEBUG("BufferedWriter open")
      if(records.isEmpty) {
        LogUtil.msggenMasterLoggerDEBUG("there are no records to process !! ")
      }
      LogUtil.msggenMasterLoggerDEBUG(s"records size is : ${records.length} ")
      for(record <- records) {
        if(Configuration.CSV.equals(fileType)) {
          outfile.write(record.toCSV() + "\n")
        } else if(Configuration.JSON.equals(fileType)) {
          outfile.write(record.toJSON() + "\n")
        }
        else {
          outfile.write(record.toString + "\n")
        }
      }
      runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
      LogUtil.logTime(s"writing to the output file took => ${runEndLocal._1} milliseconds")
      runStartLocal = runEndLocal._2
    }
    catch {
      case e: Exception => {
        LogUtil.msggenMasterLoggerDEBUG("there was an issue writing generic records to a file")
        e.printStackTrace()
      }
    }
    finally {
      LogUtil.msggenMasterLoggerDEBUG("closing our generic records file")
      outfile.close()
      runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
      LogUtil.logTime(s"closing the output file took => ${runEndLocal._1} milliseconds")
      runStartLocal = runEndLocal._2
      val runEnd = DateUtils.getDifferenceInMilliseconds(runStart)
      LogUtil.logTime(s"writeGenericRecordToFile() method time = ${runEnd._1} milliseconds")
    }
  }






  /**
    * test method for checking records
    * @param records - records to check
    */
  def check(records:RecordsTemplate):Unit = {
    val fields = records.fields
    val datatypes = records.dataTypes
    val dataformats = records.dataFormats
    val fieldlength = fields.length
    for(i <- 0 until fieldlength) {
      print(fields(i)+" ")
    }
    println()
    for(i <- 0 until fieldlength) {
      print(datatypes(i)+" ")
    }
    println()
    for(i <- 0 until fieldlength) {
      print(dataformats(i)+" ")
    }
    println()
  }

  /**
    * test method for checking record values
    * @param records - records to check
    */
  def checkValues(records:RecordsTemplate):Unit = {
    val dataValuesLength:Int = records.dataQualifiers.length
    for(i <- 0 until dataValuesLength) {
      val items = records.dataQualifiers(i)
      val itemLength:Int = items.length
      for(j <- 0 until itemLength) {
        print(items(j)+" ")
      }
      println()
    }
  }

  /**
    * this method will read in the primary key file that was made using mode1
    * the file location is specified by the config value mode1.outputfile
    * @param filepath - location of the file(mode1.outputfile)
    * @return - Array of the records in the file
    */
  def simpleReadInFile(filepath:String):Array[String] = {
    var runStartLocal = DateUtils.nowTime()
    LogUtil.msggenMasterLoggerDEBUG("entering readInPrimaryKeyFile() method")
    val primaryList:ListBuffer[String] = new ListBuffer[String]()
    val bufferedSource = Source.fromFile(filepath)
    /*for (line <- bufferedSource.getLines) {
      primaryList.add(line)
    }*/
    bufferedSource.close
    val runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
    LogUtil.logTime(s"readInPrimaryKeyFile took => ${runEndLocal._1} milliseconds")
    primaryList.toArray
  }
}
