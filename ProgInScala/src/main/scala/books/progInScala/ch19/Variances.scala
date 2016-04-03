package books.progInScala.ch19

/**
 * Created by gabbi on 03/08/14.
 */
class Variances {

}

class Publication(val title: String)

class Book(title: String, val isbn: Int) extends Publication(title)

object Library {
  val books: Set[Book] = Set(
    new Book("Programming in scala", 123),
    new Book("Art of Unix Programming", 345)
  )

  def printInfo(info: Book => Any) {
    for (book <- books) println(info(book))
  }
}

object Customer extends App {
  def getTitle(p: Publication) = p.title

  def getISBN(b: Book) = b.isbn

  Library.printInfo(getTitle)
  Library.printInfo(getISBN)
}
