package ro.jtonic.handson.springscala.service

import ro.jtonic.handson.springscala.model.Book
import ro.jtonic.handson.springscala.repo.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Antonel Ernest Pazargic on 02/07/2017.
  *
  * @author Antonel Ernest Pazargic
  */
@Service
class LibraryServiceImpl extends LibraryService {


  @Autowired
  private var bookRepository: BookRepository = _

  override def findBook(name: String): Book = bookRepository.findByName(name)

  override def save(book: Book): Unit = bookRepository.save(book)
}
