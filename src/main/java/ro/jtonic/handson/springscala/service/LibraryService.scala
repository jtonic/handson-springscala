package ro.jtonic.handson.springscala.service

import ro.jtonic.handson.springscala.model.Book

/**
  * Created by Antonel Ernest Pazargic on 02/07/2017.
  *
  * @author Antonel Ernest Pazargic
  */
trait LibraryService {

  def findBook(name: String): Book

  def save(book: Book): Unit

}
