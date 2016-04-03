package exercises.ex_31_40

import com.gabbi.profiling.PerformanceComputation.getStats
import exercises.utils.MathOps.asDigits

/**
 * Created by gabbi on 07/09/14.
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

Find the sum of all numbers which are equal to the sum of the factorial of their digits.

Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */
object Ex34 extends App {
  //isn't foldleft somewhat like dynamic programming?
  def fact(n: Int): Long = if (n == 0) 1 else (1L /: (1 to n))(_ * _)

  //caching first optimization, improvement factor of 4
  val facts1To10 = (0 to 10).toArray.map(fact)

  val upperLimit = fact(9) * 7

  def solution = (10L to upperLimit).filter { x =>
    //second optimization of not using toString, improvement by a factor of 3
    asDigits(x).map(facts1To10).sum == x
  }.sum

  println(solution)

  getStats(solution)
}
