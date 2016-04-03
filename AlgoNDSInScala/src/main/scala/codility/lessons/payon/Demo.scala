package codility.lessons.payon

/**
 * Created by gabbi on 29/08/14.
 */
object Demo extends App {
  def solution(A: Array[Int]): Int = {
    def loop(rs: Long, ls: Long, idx: Int): Int = {
      if (idx == A.length) - 1
      else {
        val newRS = rs - A(idx)
        val newLS = ls + (if ((idx - 1) < 0) 0 else A(idx - 1))
        if (newRS == newLS) idx
        else loop(newRS, newLS, idx + 1)
      }
    }

    val rightSum = A.foldLeft(0L)(_ + _)

    loop(rightSum, 0L, 0)
  }
}
