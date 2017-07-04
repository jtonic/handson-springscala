package ro.jtonic.handson.springscala.controller

import org.springframework.web.bind.annotation.{GetMapping, RestController}

/**
  * Created by Antonel Ernest Pazargic on 26/06/2017.
  *
  * @author Antonel Ernest Pazargic
  */
@RestController
class KeepAliveController {

  @GetMapping(path = Array("/keepalive"))
  def keepAlive = "KEEP_ALIVE"

  @GetMapping(path = Array("/greeting"))
  def greeting = "Hello"

}
