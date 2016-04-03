package codility.lessons.payon

/**
 * Created by gabbi on 29/08/14.
 */
object MaxMeanDiff {
  def solution(A: Array[Int]): Int = {
    // write your code in Scala 2.10
    val avg = A.foldLeft(0L)(_ + _)/A.size
    (0 until A.size).maxBy(idx => math.abs(avg - A(idx)))
  }
}
