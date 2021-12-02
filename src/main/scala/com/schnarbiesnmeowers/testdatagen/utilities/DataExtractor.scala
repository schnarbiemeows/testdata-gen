package com.schnarbiesnmeowers.testdatagen.utilities

import com.schnarbiesnmeowers.testdatagen.config.user.{ListItem, UserConfig}
import com.schnarbiesnmeowers.testdatagen.posos.{GenericRecordsTemplate, Record, RecordsTemplate}

import scala.collection.mutable.ArrayBuffer

/**
 *
 */
object DataExtractor {

  def parseUserConfiguration(config: UserConfig): RecordsTemplate = {
    println("DataExtractor object worked")
    val fieldNames = getFieldNames(config)
    val dataTypes = getDataTypes(config)
    val distinctValues = getDistinctCounts(config)  // TODO - disabling this for now
    val formats = getFormats(config)
    val qualifiers = getQualifiers(config)
    config.getFields.foreach(println)
    var records: ArrayBuffer[Record] = ArrayBuffer()
    val template:GenericRecordsTemplate = new GenericRecordsTemplate(config.getNumfiles,config.getNumrecords,config.getFilename,config.getFormat,
      fieldNames, dataTypes, formats, qualifiers, records)
    template
  }

  def getFieldNames(config: UserConfig):Array[String] = {
    config.getFields.map(rec => rec.getDataname)
  }

  def getDataTypes(config: UserConfig):Array[String] = {
    config.getFields.map(rec => rec.getDatatype match {
      case "string" => "RandomString"
      case "whole" => rec.isIsranged match {
        case true => "RangedLong"
        case _ => "RandomLong"
      }
      case "number" => "RandomDouble"
      case "boolean" => "RandomBoolean"
      case "datetime" => rec.isHastime match {
        case true => "RandomDateTime"
        case _ => "RandomDate"
      }
    })
  }

  def getDistinctCounts(config: UserConfig):Array[Int] = {
    config.getFields.map(rec => rec.isUsedistinctvals match {
      case false => 0
      case true => rec.getDistinctvalues
    })
  }

  /**
   *
   * @param config
   * @return
   */
  def getFormats(config: UserConfig):Array[String] = {
    config.getFields.map(rec => rec.getDatatype match {
      case "string" => getStringFormats(rec)
      case "whole" => rec.isIsranged match {
        case true => getWholeNumberFormats(rec)
        case _ => getWholeNumberFormats(rec)
      }
      case "number" => getDecimalNumberFormats(rec)
      case "boolean" => getBooleanFormats(rec)
      case "datetime" => rec.isHastime match {
        case true => getDateTimeFormats(rec)
        case _ => getDateTimeFormats(rec)
      }
    })
  }

  /**
   *
   * @param config
   * @return
   */
  def getQualifiers(config: UserConfig):Array[Array[String]] = {
    config.getFields.map(rec => rec.getDatatype match {
      case "string" => getStringQualifiers(rec)
      case "whole" => getWholeNumberQualifiers(rec)
      case "number" => getDecimalNumberQualifiers(rec)
      case "boolean" => getBooleanQualifiers(rec)
      case "datetime" => rec.isHastime match {
        case true => getDateTimeQualifiers(rec)
        case _ => getDateTimeQualifiers(rec)
      }
    })
  }

  /**
   *
   * @param record
   * @return
   */
  def getStringFormats(record: ListItem):String = {
    /**
     * length
     * min
     * max
     * chars = actually, it should ALWAYS have this now
     * upper = actually, this format no longer applies, replaced by mandatory chars
     * lower = actually, this format no longer applies, replaced by mandatory chars
     * nullable
     */
    record.isCanbenull match {
      case true => record.isIsfixedlength match {
        case true => "length,chars,nullable"
        case _ => "min,max,chars,nullable"
      }
      case _ => record.isIsfixedlength match {
        case true => "length,chars"
        case _ => "min,max,chars"
      }
    }
  }

  /**
   *
   * @param record
   * @return
   */
  def getWholeNumberFormats(record: ListItem):String = {
    /**
     *
     * min = only for random
     * max = only for random
     * nullable
     * linbase = only for ranged
     * linadd = only for ranged
     */
    record.isCanbenull match {
      case true => record.isIsranged match {
        case true => "linbase,linadd,nullable"
        case _ => "min,max,nullable"
      }
      case _ => record.isIsranged match {
        case true => "linbase,linadd"
        case _ => "min,max"
      }
    }
  }

  /**
   *
   * @param record
   * @return
   */
  def getDecimalNumberFormats(record: ListItem):String = {
    /**
     * length - not used currently
     * signDigits - always has this
     * min -only for random
     * max - only for random
     * linbase - only for ranged
     * linadd - only for ranged
     * roundup - not used currently
     * rounddown - not used currently
     * roundhalf - not used currently
     * nullable
     */
    record.isCanbenull match {
      case true => record.isIsranged match {
        case true => "linbase,linadd,signDigits,nullable"
        case _ => "min,max,signDigits,nullable"
      }
      case _ => record.isIsranged match {
        case true => "linbase,linadd,signDigits"
        case _ => "min,max,signDigits"
      }
    }
  }

  /**
   *
   * @param record
   * @return
   */
  def getDateTimeFormats(record: ListItem):String = {
    /**
     * start - always used
     * end - always used
     * format - always used
     * now - not being used, I need to add the ranged data choice back to the UI
     * nullable
     */
      // TODO - add the ranged data choice to the UI and add a new RangedDate and RangedDateTime options to the back end
    record.isCanbenull match {
      case true => "start,end,format,nullable"
      case _ => "start,end,format"
    }
  }

  /**
   *
   * @param record
   * @return
   */
  def getBooleanFormats(record: ListItem):String = {
    record.isCanbenull match {
      case true => "nullable"
      case _ => ""
    }
  }

  /**
   *
   * @param record
   * @return
   */
  def getStringQualifiers(record: ListItem):Array[String] = {
    /**
     * length - needs a qualifier
     * min - needs a qualifier
     * max - needs a qualifier
     * chars = actually, it should ALWAYS have this now
     * nullable
     */
    record.isCanbenull match {
      case true => record.isIsfixedlength match {
        case true =>  Array(record.getFixedlength.toString,getCharacters(record).toCharArray.toSet.mkString,record.getNullpercent.toString) //"length,chars,nullable"
        case _ => Array(record.getMinlength.toString,record.getMaxlength.toString,getCharacters(record).toCharArray.toSet.mkString,record.getNullpercent.toString)
      }
      case _ => record.isIsfixedlength match {
        case true => Array(record.getFixedlength.toString,getCharacters(record).toCharArray.toSet.mkString)
        case _ => Array(record.getMinlength.toString,record.getMaxlength.toString,getCharacters(record).toCharArray.toSet.mkString)
      }
    }
  }

  def getCharacters(record: ListItem):String = {
    val upperChars = if(record.isAllowuppers) Constants.uppers else ""
    val lowerChars = if(record.isAllowlowers) Constants.lowers else ""
    val numberChars = if(record.isAllownumbers) Constants.number else ""
    val specialChars = if(record.isAllowspecials) Constants.specials else ""
    (upperChars + lowerChars + numberChars + specialChars + record.getInclusions)
      .diff(record.getExclusions)
  }
  /**
   *
   * @param record
   * @return
   */
  def getWholeNumberQualifiers(record: ListItem):Array[String] = {
    /**
     *
     * min = only for random
     * max = only for random
     * nullable
     * linbase = only for ranged
     * linadd = only for ranged
     */
    record.isCanbenull match {
      case true => record.isIsranged match {
        case true => Array(record.getBasevalue.toString,record.getIncrement.toString,record.getNullpercent.toString)//"linbase,linadd,nullable"
        case _ => Array(record.getMinvalwhole.toString,record.getMaxvalwhole.toString,record.getNullpercent.toString)//"min,max,nullable"
      }
      case _ => record.isIsranged match {
        case true => Array(record.getBasevalue.toString,record.getIncrement.toString)//"linbase,linadd"
        case _ => Array(record.getMinvalwhole.toString,record.getMaxvalwhole.toString)//"min,max"
      }
    }
  }

  /**
   *
   * @param record
   * @return
   */
  def getDecimalNumberQualifiers(record: ListItem):Array[String] = {
    /**
     * length - not used currently
     * signDigits - always has this
     * min -only for random
     * max - only for random
     * linbase - only for ranged
     * linadd - only for ranged
     * roundup - not used currently
     * rounddown - not used currently
     * roundhalf - not used currently
     * nullable
     */
    record.isCanbenull match {
      case true => record.isIsranged match {
        case true => Array(record.getBasevaldecimal.toString,record.getIncrementdecimal.toString,record.getSigndigits.toString,record.getNullpercent.toString)//"linbase,linadd,signDigits,nullable"
        case _ => Array(record.getMinvaldecimal.toString,record.getMaxvaldecimal.toString,record.getSigndigits.toString,record.getNullpercent.toString)//"min,max,signDigits,nullable"
      }
      case _ => record.isIsranged match {
        case true => Array(record.getBasevaldecimal.toString,record.getIncrementdecimal.toString,record.getSigndigits.toString)//"linbase,linadd,signDigits"
        case _ => Array(record.getMinvaldecimal.toString,record.getMaxvaldecimal.toString,record.getSigndigits.toString)//"min,max,signDigits"
      }
    }
  }

  /**
   *
   * @param record
   * @return
   */
  def getDateTimeQualifiers(record: ListItem):Array[String] = {
    /**
     * start - always used
     * end - always used
     * format - always used
     * now - not being used, I need to add the ranged data choice back to the UI
     * nullable
     */
    // TODO - add the ranged data choice to the UI and add a new RangedDate and RangedDateTime options to the back end
    record.isCanbenull match {
      case true => Array(record.getStartdatetime,record.getEnddatetime,record.getFormat,record.getNullpercent.toString)//"start,end,format,nullable"
      case _ => Array(record.getStartdatetime,record.getEnddatetime,record.getFormat)//"start,end,format"
    }
  }

  /**
   *
   * @param record
   * @return
   */
  def getBooleanQualifiers(record: ListItem):Array[String] = {
    record.isCanbenull match {
      case true => Array(record.getNullpercent.toString)
      case _ => Array()
    }
  }
}
