package codility.lessons.one;

/**
 * Created by gabbi on 16/09/14.
 */
object MissingElement {
  def solution(A: Array[Int]): Int = {
    // write your code in Scala 2.10
    ((1 to A.length + 1).foldLeft(0L)(_ + _) - A.sum).toInt
  }
}
