package codility.lessons.three

/**
 * Created by gabbi on 22/09/14.
 */
object PassingCars {
  def solution(A: Array[Int]): Int = {
    // write your code in Scala 2.10
    val res = A.foldLeft((0L,0L)){
      case ((numZeros, passingCars), x) =>
        if(x == 0) (numZeros+1, passingCars)
        else (numZeros, passingCars+numZeros)
    }
    val count = res._2
    if(count > 1000000000) -1 else count.toInt
  }
}
