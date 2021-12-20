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
    val dataTypesAndDateTimeFormats:Array[Tuple2[String,String]] = getDataTypes(config)
    val dataTypes:Array[String] = dataTypesAndDateTimeFormats.map(rec => rec._1)
    val dateTimeFormats:Array[String] = dataTypesAndDateTimeFormats.map(rec => rec._2)
    val distinctValues = getDistinctCounts(config)  // TODO - disabling this for now
    val formats = getFormats(config)
    val qualifiers = getQualifiers(config)
    config.getFields.foreach(println)
    var records: ArrayBuffer[Record] = ArrayBuffer()
    val template:GenericRecordsTemplate = new GenericRecordsTemplate(config.getNumfiles,config.getNumrecords,config.getFilename,config.getFormat,
      fieldNames, dataTypes, dateTimeFormats, formats, qualifiers, records)
    template
  }

  def getFieldNames(config: UserConfig):Array[String] = {
    config.getFields.map(rec => rec.getDataname)
  }

  def getDataTypes(config: UserConfig):Array[Tuple2[String,String]] = {
    config.getFields.map(rec => rec.getDatatype match {
      case "string" => ("RandomString","none")
      case "whole" => rec.isIsranged match {
        case true => ("RangedLong","none")
        case _ => ("RandomLong","none")
      }
      case "number" => rec.isIsranged match {
        case true => ("RangedDouble","none")
        case _ => ("RandomDouble","none")
      }
      case "boolean" => ("RandomBoolean","none")
      case "datetime" => rec.isHastime match {
        case true => rec.isIsranged match {
          case true=> ("RangedDateTime",rec.getFormat)
          case _ => ("RandomDateTime",rec.getFormat)
        }
        case _ => rec.isIsranged match {
          case true=> ("RangedDate",rec.getFormat)
          case _ => ("RandomDate",rec.getFormat)
        }
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
        case _ => getDateFormats(rec)
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
        case _ => getDateQualifiers(rec)
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
     * length - padding length TODO - offer in phase 2
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
  def getDateFormats(record: ListItem):String = {
    /**
     * start - only used for RandomDate and RandomDateTime
     * end - only used for RandomDate and RandomDateTime
     * format - always used
     * now - not being used, I need to add the ranged data choice back to the UI
     * basedate - only used for RangedDate and RangedDateTime
     * incyr - only used for RangedDate and RangedDateTime
     * incmth - only used for RangedDate and RangedDateTime
     * incday - only used for RangedDate and RangedDateTime
     * nullable
     */
    record.isCanbenull match {
      case true => record.isIsranged match {
        case true => "basedate,incyr,incmth,incday,format,nullable"
        case _ => "start,end,format,nullable"
      }
      case _ => record.isIsranged match {
        case true => "basedate,incyr,incmth,incday,format"
        case _ => "start,end,format"
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
     * start - only used for RandomDate and RandomDateTime
     * end - only used for RandomDate and RandomDateTime
     * format - always used
     * now - not being used, I need to add the ranged data choice back to the UI
     * basedate - only used for RangedDate and RangedDateTime
     * incyr - only used for RangedDate and RangedDateTime
     * incmth - only used for RangedDate and RangedDateTime
     * incday - only used for RangedDate and RangedDateTime
     * inchr - only used for RangedDate and RangedDateTime
     * incmin - only used for RangedDate and RangedDateTime
     * incsec - only used for RangedDate and RangedDateTime
     * nullable
     */
    record.isCanbenull match {
      case true => record.isIsranged match {
        case true => "basedate,incyr,incmth,incday,inchr,incmin,incsec,format,nullable"
        case _ => "start,end,format,nullable"
      }
      case _ => record.isIsranged match {
        case true => "basedate,incyr,incmth,incday,inchr,incmin,incsec,format"
        case _ => "start,end,format"
      }
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
     * length - padding length TODO - offer in phase 2
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
  def getDateQualifiers(record: ListItem):Array[String] = {
    /**
     * start - only used for RandomDate and RandomDateTime
     * end - only used for RandomDate and RandomDateTime
     * format - always used
     * now - not being used, I need to add the ranged data choice back to the UI
     * basedate - only used for RangedDate and RangedDateTime
     * incyr - only used for RangedDate and RangedDateTime
     * incmth - only used for RangedDate and RangedDateTime
     * incday - only used for RangedDate and RangedDateTime
     * nullable
     */
    record.isCanbenull match {
      case true => record.isIsranged match {
        case true => Array(record.getBasedatetime,record.getInc_yr_str.toString,record.getInc_mth_str.toString,
          record.getInc_day_str.toString,record.getFormat,record.getNullpercent.toString)
        // "basedate,incyr,incmth,incday,format,nullable"
        case _ => Array(record.getStartdatetime,record.getEnddatetime,record.getFormat,record.getNullpercent.toString)
      }
      case _ => record.isIsranged match {
        case true => Array(record.getBasedatetime,record.getInc_yr_str.toString,record.getInc_mth_str.toString,
          record.getInc_day_str.toString,record.getFormat)
        case _ => Array(record.getStartdatetime,record.getEnddatetime,record.getFormat)
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
     * start - only used for RandomDate and RandomDateTime
     * end - only used for RandomDate and RandomDateTime
     * format - always used
     * now - not being used, I need to add the ranged data choice back to the UI
     * basedate - only used for RangedDate and RangedDateTime
     * incyr - only used for RangedDate and RangedDateTime
     * incmth - only used for RangedDate and RangedDateTime
     * incday - only used for RangedDate and RangedDateTime
     * inchr - only used for RangedDate and RangedDateTime
     * incmin - only used for RangedDate and RangedDateTime
     * incsec - only used for RangedDate and RangedDateTime
     * nullable
     */
    record.isCanbenull match {
      case true => record.isIsranged match {
        case true => Array(record.getBasedatetime,record.getInc_yr_str.toString,record.getInc_mth_str.toString,
          record.getInc_day_str.toString,record.getInc_hrs_str.toString,record.getInc_min_str.toString,
          record.getInc_sec_str.toString,record.getFormat,record.getNullpercent.toString)
        // "basedate,incyr,incmth,incday,format,nullable"
        case _ => Array(record.getStartdatetime,record.getEnddatetime,record.getFormat,record.getNullpercent.toString)
      }
      case _ => record.isIsranged match {
        case true => Array(record.getBasedatetime,record.getInc_yr_str.toString,record.getInc_mth_str.toString,
          record.getInc_day_str.toString,record.getInc_hrs_str.toString,record.getInc_min_str.toString,
          record.getInc_sec_str.toString,record.getFormat)
        case _ => Array(record.getStartdatetime,record.getEnddatetime,record.getFormat)
      }
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
