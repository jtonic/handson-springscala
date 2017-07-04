package ro.jtonic.handson.springscala.controller

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

/**
  * Created by Antonel Ernest Pazargic on 26/06/2017.
  *
  * @author Antonel Ernest Pazargic
  */
@RestController
@RequestMapping(path = Array("/keepalive"))
class KeepAliveController {

  @GetMapping
  def keepAlive = {
    "KEEP_ALIVE"
  }

}
