package blogs.beautifulFolds

/**
  * Created by gabbi on 18/12/2016.
  */

import blogs.beautifulFolds.Fold._
import cats.implicits.catsKernelStdGroupForInt

object FoldTest extends App {
  private val sequence = 1 to 10

  val s: Fold[Int, Int] = sum[Int]
  println(fold(sequence)(s))
}
