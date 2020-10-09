package com.schanrbiesnmeowers.testdatagen.controller

import com.schanrbiesnmeowers.testdatagen.config._
import com.schanrbiesnmeowers.testdatagen.config.ui.InitialConfig
import com.schanrbiesnmeowers.testdatagen.config.user.UserConfig
import com.schanrbiesnmeowers.testdatagen.posos.{JavaPoso, ResponseMessage, SimpleCaseClass, WithConstr}
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, PostMapping, RequestBody, RequestMapping, RestController}

@CrossOrigin
@RestController
@RequestMapping(Array("/testdata-gen"))
class ScalaController {


  @GetMapping(Array("/config"))
  def getInitialConfig(): ResponseEntity[Object] = {
    val config = new InitialConfig
    println(s"returning config with: ${config.stringOps.minlength} for minlength and ${config.stringOps.maxlength} for maxlength")
    println(config.dateTimeOps.getDateFormats())
    ResponseEntity.status(HttpStatus.OK).body(config)
  }

  @PostMapping(Array("/userconfig"))
  def setWithConstr2(@RequestBody config: UserConfig): ResponseEntity[Object] = {
    println(config.getNumrecords + " - " + config.getFormat)
    ResponseEntity.status(HttpStatus.OK).body(config)
  }
}
