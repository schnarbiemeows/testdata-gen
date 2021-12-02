/*
 * Created 2019 by Dylan Kessler
 */

package com.schnarbiesnmeowers.testdatagen.actors.messages

/**
  * this is an message object that is sent from the RecordsMakerControllerActor
  * to the RecordsMakerAndCopierActor actors. It is a request to create a record
  * in an array
  * @param number - index into the array where the record should go
  */
case class MakeRecordMessage(number:String) {

}
