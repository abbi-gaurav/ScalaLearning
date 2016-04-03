package codility.lessons.one;

/**
 * Created by gabbi on 19/09/14.
 */
object CheckIfPermutation {
  def solution(A: Array[Int]): Int = {
    // write your code in Scala 2.10
    if (A.exists(x => x < 0 || x > A.length)) 0
    else {
      val testArr = Array.fill(A.length + 1) {
        0
      }
      A.foreach(x => testArr(x) = 1)
      if(A.length == testArr.sum) 1 else 0
    }
  }
}
