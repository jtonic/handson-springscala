package ro.jtonic.handson.springscala

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

/**
  * Created by Antonel Ernest Pazargic on 26/06/2017.
  *
  * @author Antonel Ernest Pazargic
  */
object Application extends App {
    SpringApplication.run(classOf[Config])
}


@SpringBootApplication
class Config {

  import org.springframework.boot.CommandLineRunner
  import org.springframework.context.annotation.Bean

  @Bean
  def commandLineRunner(): CommandLineRunner = (_: Array[String]) => println("The application has been started.")

}

