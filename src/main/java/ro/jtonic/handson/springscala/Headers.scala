package ro.jtonic.handson.springscala

/**
  * Created by Antonel Ernest Pazargic on 04/07/2017.
  *
  * @author Antonel Ernest Pazargic
  */

object Headers extends Headers

sealed trait Headers {
  final val X_ACCESS_TOKEN = "X-AccessToken"
}
