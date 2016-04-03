package codility.lessons.two

/**
 * Created by gabbi on 22/09/14.
 */
object MaxCounter extends App{
  def solution(N: Int, A: Array[Int]): Array[Int] = {
    // write your code in Scala 2.10
    var allMaxValue = 0
    var max = 0
    val result = A.foldLeft(Array.fill(N) {
      0
    }) {
      case (acc, value) =>
        if (1 <= value && value <= N) {
          if (acc(value - 1) < allMaxValue) {
            acc(value - 1) = allMaxValue+1
          } else {
            acc(value - 1) = acc(value - 1) + 1
          }
          if (acc(value - 1) > max) {
            max = acc(value - 1)
          }
        } else if (value == N + 1) {
          allMaxValue = max
        }
        acc
    }

    for (i <- 0 until result.length) {
      if (result(i) < allMaxValue) {
        result(i) = allMaxValue
      }
    }
    result
  }

  solution(5, Array(3, 4, 4, 6, 1, 4, 4))
}
