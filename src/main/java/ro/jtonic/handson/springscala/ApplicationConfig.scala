package ro.jtonic.handson.springscala

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import ro.jtonic.handson.springscala.service.LibraryService

/**
  * Created by Antonel Ernest Pazargic on 02/07/2017.
  *
  * @author Antonel Ernest Pazargic
  */
@SpringBootApplication
@EnableJpaRepositories(Array("ro.jtonic.handson.springscala.repo"))
@EntityScan(Array("ro.jtonic.handson.springscala.model"))
class ApplicationConfig {

  @Autowired
  private var libraryService: LibraryService = _

  @Bean
  def commandLineRunner: CommandLineRunner =
    new CommandLineRunner {
      override def run(args: String*): Unit = {
        import ro.jtonic.handson.springscala.model.Book
        println("The application has been started.")
        Console println "Saving a book in the db"
        assert(libraryService != null)
        val bookName = "Programming in Scala"
        val bookAuthor = "Martin Odersky"
        libraryService save new Book(bName = bookName, bAuthor = bookAuthor)

        Console println s"Finding the book by the name = $bookName"
        libraryService findBook bookName
      }
    }
}
