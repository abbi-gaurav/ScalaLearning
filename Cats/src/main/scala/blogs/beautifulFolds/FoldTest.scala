package blogs.beautifulFolds

/**
  * Created by gabbi on 18/12/2016.
  */

import blogs.beautifulFolds.Fold._
import cats.implicits._

object FoldTest extends App {
  private val sequence = 1 to 10

  val s: Fold[Int, Int] = sum[Int]
  println(fold(sequence)(s))

  private def even(i: Int): Boolean = i % 2 == 0

  private def negative(i: Int): Boolean = i < 0

  println(fold(sequence)(first))
  println(fold(sequence)(last))
}
