package codility.lessons.two

/**
 * Created by gabbi on 22/09/14.
 */
object MinMissing extends App {
  def solution(A: Array[Int]): Int = {
    // write your code in Scala 2.10
    val arr = Array.fill(A.length) {
      false
    }
    A.foreach { x =>
      if (x > 0 && x <= A.length) {
        arr(x - 1) = true
      }
    }
    val res = arr.indexWhere(_ == false) + 1
    if (res == 0) A.length+1 else res
  }

  println(solution(Array(1)))
}
