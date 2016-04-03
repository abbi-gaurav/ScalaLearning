package exercises.ex_31_40

import com.gabbi.profiling.PerformanceComputation._

/**
 * Created by gabbi on 30/08/14.
 */
object Ex32 extends App {
  /**
   * only positive numbers expected
   * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
   * @param n
   * @return
   */
import exercises.utils.MathOps._

  def sum:Int= (for {
    n: Int <- (2 until 100).toSet
    m: Int <- (if (n < 9) 1234 else 123) until (10000 / n)
    prod = m * n
    number: Long = concat(concat(m, n), prod)
    if isPandigital(number)
  } yield prod).sum

  println(getStats(sum))
}
