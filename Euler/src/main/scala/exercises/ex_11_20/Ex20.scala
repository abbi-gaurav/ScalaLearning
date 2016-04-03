package exercises.ex_11_20

/**
 * Created by gabbi on 11/07/14.
 */
/**
 * n! means n × (n − 1) × ... × 3 × 2 × 1

For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

Find the sum of the digits in the number 100!
 */

object Ex20 extends App {
  def fib(n: Int): BigInt = (1 to n).foldLeft(BigInt(1))(_ * _)
  val res = fib(100).toString().map(_.asDigit).sum
  println(res)
}
