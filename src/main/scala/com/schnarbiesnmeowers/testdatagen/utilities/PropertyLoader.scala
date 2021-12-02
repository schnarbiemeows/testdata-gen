/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.utilities

import java.io.{FileInputStream, IOException}
import java.util.Properties
/**
  * object for loading the contents of the config file located at /home/ubuntu/config/
  */
object PropertyLoader {

  /**
    * our global properties
    */
  var props:Option[Properties] = None
  /**
    * method for reading in the properties from a text file
    * @param configFileName - name of the config file to load
    * @return - Properties object of all of the file properties
    */
  def getProperties(configFileName:String): Properties = {
    try {
      val prop = new Properties()
      val fullpath = adjustConfigFilePath(configFileName)
      println(s"loading properties from file: ${fullpath}")
      prop.load(new FileInputStream(fullpath))
      props = Some(prop)
      println("properties loaded")
      setLogger(prop)
      prop
    } catch {
      case e: IOException =>
        //LogUtil.msggenThread1LoggerERROR(s"error loading properties from file: ${fullpath}")
        e.printStackTrace()
        sys.exit(1)
    }
  }

  /**
    * method to detect if the operating system is windows, and, if so, to
    * replace any "\" in the file path with "\\"
    * @param configFileName - full path to the config file
    * @return - adjusted config file path
    */
  def adjustConfigFilePath(configFileName:String):String = {
    val osName = System.getProperty("os.name")
    var configFileNameadjusted:String = configFileName
    if(osName.toLowerCase.contains("windows")) {
      println("windows file was found")
      val configFileNameadjusted = configFileName.replace("\\\\","\\").replace("\\","\\\\")
    }
    println(s"file name = $configFileNameadjusted")
    configFileNameadjusted
  }

  /**
    * method to detect if the operating system is windows, and, if so, to
    * replace any "\" in the file path with "\\"
    * @param configFileName - full path to the config file
    * @return - adjusted config file path
    */
  def revAdjustConfigFilePath(configFileName:String):String = {
    val osName = System.getProperty("os.name")
    var configFileNameadjusted:String = configFileName
    if(osName.toLowerCase.contains("windows")) {
      println("windows file was found")
      configFileNameadjusted = configFileName.replaceAll("\\\\","\\\\\\\\")
    }
    println(s"file name = $configFileNameadjusted")
    configFileNameadjusted
  }

  /**
    * method to set the root logger in the System prperties, for the log42j logging system
    * @param prop - value to set the rootlogger to
    */
  def setLogger(prop: Properties): Unit = {
    val rootLogger:String = prop.getProperty(Configuration.ROOT_LOGGER).toString
    System.setProperty("rootlogger",rootLogger)
  }

  /**
    * method to retrieve a given property
    * @param property - property to retrieve
    * @return - String
    */
  def getProperty(property: String): String = {
    if(props==None) {
      getProperties(Configuration.PROPERTY_FILE_LOCATION)
    }
    props.get.getProperty(property).toString
  }

  /**
    * method(only used by test code) to set a property
    * @param property - property to set
    * @param value - value of this property
    */
  def setProperty(property: String, value: String): Unit = {
    if(props==None) {
      getProperties(Configuration.PROPERTY_FILE_LOCATION)
    }
    props.get.setProperty(property, value)
  }
}
