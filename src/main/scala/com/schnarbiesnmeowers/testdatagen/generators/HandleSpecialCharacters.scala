package com.schnarbiesnmeowers.testdatagen.generators

trait HandleSpecialCharacters {

  def handleSpecialCharacters(input: String, fileType: String):String = {
    fileType match {
      case "MYSQL" => input.toCharArray.map(rec => rec match {
        case '\\' => "\\\\"
        case '\'' => "\\'"
        /*case '\"' => "\\\""*/
        case _ => rec
      }).mkString
      case "JSON" => input.toCharArray.map(rec => rec match {
        case '\\' => "\\\\"
        case '\"' => "\\\""
        case _ => rec
      }).mkString
      case "," => input.toCharArray.map(rec => rec match {
        case ',' => ""
        case _ => rec
      }).mkString
      case "|" => input.toCharArray.map(rec => rec match {
        case '|' => ""
        case _ => rec
      }).mkString
      case _ => input
    }
  }

  def handleNullsForJson(input: String):String = {
    input match {
      case "" => "null"
      case _ => input
    }
  }
}
