package ro.jtonic.handson.springscala.repo

import java.lang.{Long => JLong}

import org.springframework.data.repository.CrudRepository
import ro.jtonic.handson.springscala.model.Book

/**
  * Created by Antonel Ernest Pazargic on 02/07/2017.
  *
  * @author Antonel Ernest Pazargic
  */
trait BookRepository extends CrudRepository[Book, JLong] {
  def findByName(name: String): Book
}
