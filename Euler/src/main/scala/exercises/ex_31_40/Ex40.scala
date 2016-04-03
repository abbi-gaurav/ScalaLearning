package exercises.ex_31_40

/**
 * Created by gabbi on 23/10/14.
 * An irrational decimal fraction is created by concatenating the positive integers:

0.123456789101112131415161718192021...

It can be seen that the 12th digit of the fractional part is 1.

If dn represents the nth digit of the fractional part, find the value of the following expression.

d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
 */
object Ex40 extends App {
  val digitsInRange: List[Long] = (1 to 6).toList.map { x =>
    9 * x * math.pow(10, x - 1).toLong
  }

  def findNthDigit(n: Long): Int = {
    if (n < 10) n.toInt
    else {
      def loop(digitCount: Int, range: List[Long], accumultor: Long): Int = {
        if (range.head < n) loop(digitCount + 1, range.tail, accumultor - range.head)
        else {
          val l: Int = if(accumultor% digitCount == 0) 0 else digitCount - (accumultor % digitCount).toInt
          val x = accumultor + l
          val number = math.pow(10, digitCount - 1).toLong + (x / digitCount) - 1
          val str = number.toString
          str.charAt(str.size - 1 - l).asDigit
        }
      }

      loop(2, digitsInRange.tail, n - 9)
    }
  }
  val res = (0 to 6).map(math.pow(10, _).toLong).map(findNthDigit).toList
  println(res.product)
  println(res)
}
