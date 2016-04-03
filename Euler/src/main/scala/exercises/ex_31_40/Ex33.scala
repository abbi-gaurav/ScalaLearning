package exercises.ex_31_40

import com.gabbi.profiling.PerformanceComputation._

/**
 * Created by gabbi on 04/09/14.
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.

If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 */
object Ex33 extends App {
  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def solution1 = {
    val pairs = for {
      i <- 1 until 10
      d <- 1 until i
      n <- 1 until d
      num: Int = 10 * n + i
      denom: Int = 10 * i + d
      if num * d == denom * n
    } yield (n, d)
    val product = pairs.reduceLeft((r1, r2) => (r1._1 * r2._1, r1._2 * r2._2))
    val g = gcd(product._1, product._2)
    product._2 / g
  }

  println(solution1)

  getStats(solution1)
}
