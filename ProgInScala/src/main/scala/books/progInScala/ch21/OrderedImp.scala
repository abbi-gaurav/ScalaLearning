package books.progInScala.ch21

/**
 * Created by gabbi on 15/08/14.
 */
object OrderedImp extends App {
  def maxListImp[T](list: List[T])(implicit orderer: T => Ordered[T]): T = list match {
    case List() => throw new IllegalArgumentException
    case List(x) => x
    case x :: xs =>
      val maxRest = maxListImp(xs)
      if (x > maxRest) x else maxRest
  }

  println(maxListImp(List(9, 87, 2, 0, 4)))
}
