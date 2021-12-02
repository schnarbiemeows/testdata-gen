/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.actors.messages

/**
  * this is an actor message that is sent from the RecordMakerController class
  * to the RecordsMakerControllerActor actor, telling it to begin the record
  * making/copying/writing process
  */
case class StartMessage() {

}
