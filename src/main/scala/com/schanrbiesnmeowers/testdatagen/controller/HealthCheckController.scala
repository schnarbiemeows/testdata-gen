package com.schanrbiesnmeowers.testdatagen.controller

import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class HealthCheckController {

  @GetMapping(Array("/"))
  def healthcheck(): String = {
    "hello from testdata-gen!"
  }
}
