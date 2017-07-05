package ro.jtonic.handson.springscala.model

import java.lang.{Long => JLong}
import javax.persistence.{Column, Entity, GeneratedValue, GenerationType, Id, Table}

import scala.beans.BeanProperty

/**
  * Created by Antonel Ernest Pazargic on 02/07/2017.
  *
  * @author Antonel Ernest Pazargic
  */
@Entity
@Table(name = "book")
class Book(bName: String, bAuthor: String) {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) @BeanProperty var id: JLong = _
  @Column(nullable = false, unique = true) @BeanProperty var name: String = bName
  @Column(nullable = false) @BeanProperty var author: String = bAuthor

  def this() = this(null, null)
}
