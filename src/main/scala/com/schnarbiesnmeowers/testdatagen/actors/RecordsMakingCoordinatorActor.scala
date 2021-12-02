/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.actors

import akka.actor.{Actor, ActorRef, Props}

import java.util.concurrent.ArrayBlockingQueue
import com.schnarbiesnmeowers.testdatagen.actors.messages._
import com.schnarbiesnmeowers.testdatagen.posos.{Record, RecordsTemplate}
import com.schnarbiesnmeowers.testdatagen.utilities.{Configuration, DateUtils, LogUtil, PropertyLoader, StringUtils}

/**
  * this is an actor class. This class is the parent controller actor for a process
  * that makes,copies, and then creates text files of data
  * @param template - the template containing the input spreadsheet data information
  * @param finishedQueue - an ArrayBlockingQueue that this controller uses to tell the
  *                      RecordMakerController that we are done creating data
  */
class RecordsMakingCoordinatorActor(val template: RecordsTemplate,
                                    val finishedQueue:ArrayBlockingQueue[Array[Record]])
  extends Actor {

  // TODO - do we want more timing logs in here?
  /**
    * N = numThreads to use for making/copying Record data
    */
  val numThreads:Int = 1//PropertyLoader.getProperty(Configuration.MODE4_NUM_THREADS).toString.toInt
  LogUtil.msggenMasterLoggerDEBUG("")
  LogUtil.msggenMasterLoggerDEBUG(s"numTheads = ${numThreads}")
  /**
    * F = numFiles - number of output csv or json files to make with Record data
    */
  val numFiles:Int = 1 //PropertyLoader.getProperty(Configuration.MODE4_NUM_FILES).toString.toInt
  LogUtil.msggenMasterLoggerDEBUG(s"numFiles = ${numFiles}")
  /**
    * R = numRecordsPerFile - number of Record records in each output file
    */
  val numRecordsPerFile:Int = template.numrecords//PropertyLoader.getProperty(Configuration.MODE4_NUM_RECORDS).toString.toInt
  LogUtil.msggenMasterLoggerDEBUG(s"numRecordsPerFile = ${numRecordsPerFile}")
  LogUtil.msggenMasterLoggerDEBUG("")
  /**
    * primary array for storing an intial batch of created Record objects
    */
  var recordArray1:Array[Record] = new Array[Record](numRecordsPerFile)

  /**
    * actors that will be used for making the records and transferring them from
    * recordArray1 to recordArray2
    */
  var actors:Array[ActorRef] = new Array[ActorRef](numThreads)
  for(i <- 0 until actors.length) {
    //LogUtil.msggenMasterLoggerDEBUG("creating a new RecordMakerAndCopierActor")
    actors(i) = context.actorOf(Props(new RecordMakerActor(template,recordArray1,i)))
  }

  /**
    * indexes and toggles used to keep track of how many records have been requested to
    * be made or copied, how many records have successfully been made or copied, how many
    * files havebeen requested to be created, and how many have been created.
    *
    * there are 3 phases to the process:
    * 1. record making phase
    * 2. record copying phase
    * 3. file creation(csv or json)
    *
    * each phases will occur F times, because each file will need records made for it,
    * copied for it, and then finally written out to file
    * phase 3 for a file can run concurrently with phases 1 and 2 for the next file,
    * but in general, phases 1 and 2 for a given file
    * should happen in series
    */
  var fileArrayIndex:Int = 0
  var makeRecordCount:Int = 0
  var recordMadeCount:Int = 0

  /**
    * main actor method
    * @return - Nothing
    */
  override def receive: Receive = {
    case StartMessage => initiateRecordMaking()
    case FinishedMakingRecordMessage(threadNum) => incrementCheckAndCount(threadNum) // REMOVE EVERYTHING AFTER THIS
  }

  /**
    * this method is used to initiate a record making phase. We need to initiate
    * the phase by sending N requests to each of the N actors we are using
    * then we need to toggle the inRecordMakingPhase on to indicate that we are
    * in a record making phase
    */
  def initiateRecordMaking():Unit = {
    LogUtil.msggenMasterLoggerDEBUG("")
    LogUtil.msggenMasterLoggerDEBUG("inside initiateRecordMaking")
    for(i <- 0 until actors.length) {
      actors(i) ! MakeRecordMessage(makeRecordCount+","+fileArrayIndex)
      makeRecordCount+=1
    }
    LogUtil.msggenMasterLoggerDEBUG(s"made ${makeRecordCount} records")
    LogUtil.msggenMasterLoggerDEBUG("")
  }

  /**
    * method that gets invoked each time a FinishedMakingRecordMessage message comes back
    * from a RecordMakerAndCopierActor
    * @param number - the actor number of the sender
    */
  def incrementCheckAndCount(number:Int):Unit = {
    recordMadeCount+=1
    LogUtil.msggenMasterLoggerDEBUG(s"inside incrementCheckAndCount, records made = ${recordMadeCount}")
    if(makeRecordCount<numRecordsPerFile) {
      LogUtil.msggenMasterLoggerDEBUG(s"sending another MakeRecordMessage(${makeRecordCount})")
      actors(number) ! MakeRecordMessage(makeRecordCount+","+fileArrayIndex)
      makeRecordCount+=1
    } else {
      if (recordMadeCount == numRecordsPerFile) {
        LogUtil.msggenMasterLoggerDEBUG("turning off record making phase")
        finishedQueue.put(recordArray1)
      }
    }
  }

}
