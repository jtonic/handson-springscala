package ro.jtonic.handson.springscala

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.context.annotation.Bean


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

  @Bean
  def commandLineRunner: CommandLineRunner =
    new CommandLineRunner {
      override def run(args: String*): Unit = println("The application has been started.")
    }

}

