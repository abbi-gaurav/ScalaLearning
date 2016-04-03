package books.progInScala.ch20

/**
 * Created by gabbi on 04/08/14.
 */
trait Rational {
  val numer:Int
  val denom:Int

  require(denom != 0)

  override def toString = numer + "/" + denom
}
