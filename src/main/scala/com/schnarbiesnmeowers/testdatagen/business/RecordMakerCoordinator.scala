/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.business

import akka.actor.{ActorSystem, Props}
import com.schnarbiesnmeowers.testdatagen.actors.RecordsMakingCoordinatorActor
import com.schnarbiesnmeowers.testdatagen.actors.messages.StartMessage
import com.schnarbiesnmeowers.testdatagen.posos.{Record, RecordsTemplate}
import com.schnarbiesnmeowers.testdatagen.utilities.{DateUtils, LogUtil}

import java.time.LocalTime
import java.util.concurrent.ArrayBlockingQueue

/**
  * this class is used as a primary to initiate the actor system that will generate records
  * @param template - Record template
  */
class RecordMakerCoordinator(val template: RecordsTemplate) {

  /**
    * main method to invoke the actors
    * @return - success indicator
    */
  def generateRecords(): Array[Record] = {
    val runStart = DateUtils.nowTime()
    var runStartLocal = DateUtils.nowTime()
    var runEndLocal:Tuple2[Long, LocalTime] = (0,runStartLocal)
    LogUtil.msggenMasterLoggerDEBUG("inside RecordMakerController.generateRecords main method");
    val successfull = true
    val blockingQueue = new ArrayBlockingQueue[Array[Record]](1)       // NEED TO CHANGE THIS TO HANDLE ARRAY[Record]
    LogUtil.msggenMasterLoggerDEBUG("initializing the actor system")
    val system = ActorSystem("RecordMakingActorSystem")
    LogUtil.msggenMasterLoggerDEBUG("creating the primary record making controller")
    val recordsMakerControllerActor = system.actorOf(Props(new RecordsMakingCoordinatorActor(template,
      blockingQueue)), name = "recordsMakerControllerActor")
    runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
    LogUtil.logTime(s"creating the actor system took => ${runEndLocal._1} milliseconds")
    runStartLocal = runEndLocal._2
    LogUtil.msggenMasterLoggerDEBUG("sending a StartMessage to the primary record making controller")
    recordsMakerControllerActor ! StartMessage
    runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
    LogUtil.logTime(s"sending the message to initiate the actor system took => ${runEndLocal._1} milliseconds")
    runStartLocal = runEndLocal._2
    LogUtil.msggenMasterLoggerDEBUG("polling the ArrayBlockingQueue")
    val generatedRecords:Array[Record] = blockingQueue.take()
    runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
    //LogUtil.logTime(s"sending the message to initiate the actor system took => ${runEndLocal._1} milliseconds")
    runStartLocal = runEndLocal._2
    LogUtil.msggenMasterLoggerDEBUG("shutting down the actor system")
    system.terminate()
    runEndLocal = DateUtils.getDifferenceInMilliseconds(runStartLocal)
    LogUtil.logTime(s"terminating the actor system took => ${runEndLocal._1} milliseconds")
    runStartLocal = runEndLocal._2
    val runEnd = DateUtils.getDifferenceInMilliseconds(runStart)
    LogUtil.logTime(s"RecordMakerController.generateRecords run() method time = ${runEnd._1} milliseconds")
    generatedRecords
  }
}
