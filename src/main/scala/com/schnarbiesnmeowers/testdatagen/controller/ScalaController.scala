package com.schnarbiesnmeowers.testdatagen.controller

import com.schnarbiesnmeowers.testdatagen.business.{DataCreationProcessor}
import com.schnarbiesnmeowers.testdatagen.config.user.UserConfig
import com.schnarbiesnmeowers.testdatagen.config.ui.InitialConfig
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{CrossOrigin, GetMapping, PostMapping, RequestBody, RequestMapping, RestController}

@CrossOrigin
@RestController
@RequestMapping(Array("/testdata-gen"))
class ScalaController {

  /*@GetMapping(Array("/config"))
  def getInitialConfig(): ResponseEntity[Object] = {
    val config = new InitialConfig
    println(s"returning config with: ${config.stringOps.minlength} for minlength and ${config.stringOps.maxlength} for maxlength")
    println(config.dateTimeOps.getDateFormats())
    ResponseEntity.status(HttpStatus.OK).body(config)
  }*/

  @GetMapping(Array("/healthcheck"))
  def healthcheck(): String = {
    "hello from testdata-gen!"
  }

  @PostMapping(Array("/makedata"))
  def generateData(@RequestBody config: UserConfig): ResponseEntity[Object] = {
    val processor:DataCreationProcessor = new DataCreationProcessor(config)
    val result = processor.runForTestingOnly(); // for testing using Postman(and unit tests)
    //val result = processor.run();
    ResponseEntity.status(HttpStatus.OK).body(result)
  }
}
