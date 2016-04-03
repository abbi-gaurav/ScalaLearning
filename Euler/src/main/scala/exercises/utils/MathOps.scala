package exercises.utils

/**
 * Created by gabbi on 07/09/14.
 */
object MathOps {
  def asDigits(x: Long): List[Int] = {
    def loop(n: Long, acc: List[Int]): List[Int] = {
      if (n == 0) acc
      else loop(n / 10, (n % 10).toInt :: acc)
    }
    loop(x, List())
  }

  def list2Long(list: List[Int]): Long = {
    list.zip(list.length - 1 to 0 by -1).foldLeft(0L) {
      case (acc, (num, pos)) => acc + (num * math.pow(10, pos).toLong)
    }
  }

  def rotations(number: Int): List[Long] = {
    val digits = asDigits(number)
    def loop(acc: List[Long], rotatedDigits: List[Int]): List[Long] = {
      val rotatedNumber: Long = list2Long(rotatedDigits)
      if (rotatedNumber == number) acc
      else {
        loop(rotatedNumber :: acc, rotatedDigits.tail ::: List(rotatedDigits.head))
      }
    }
    loop(List(number), digits.tail ::: List(digits.head))
  }

  def isPrime(number: Long) = number > 1 && (number == 2 || (2 to math.sqrt(number).toInt).forall(number % _ > 0))

  lazy val ps: Stream[Int] = 2 #:: Stream.from(3).filter(i => ps.takeWhile(j => j * j <= i).forall(i % _ > 0))

  def isPandigital(n: Long, start:Int = 1): Boolean = {
    def loop(x: Long, digits: Int, count: Int): Boolean = {
      //number of 1's in digits is equal to the length of the number
      if (x == 0) digits == (1 << count) - 1
      else {
        //get a 1 placed on the digits value(O based idx)
        val nextDigits = digits | 1 << ((x % 10) - start)
        //if nextDigits is not changed, then it is same digit repeated here
        if (nextDigits == digits) false
        //tail recursion
        else loop(x / 10, nextDigits, count + 1)
      }
    }
    loop(n, 0, 0)
  }

  def concat(x: Long, y: Long): Long = x * math.pow(10, (math.log10(y) + 1).toInt).toLong + y

  def partialSums(list:List[Int]):List[Long] = {
    def loop(xs:List[Int], acc:List[Long]):List[Long] = xs match {
      case Nil => acc
      case y::ys => {
        val newTotal = (if(acc.isEmpty) 0 else acc.head) + y
        loop(ys, newTotal :: acc )
      }
    }
    loop(list, List()).reverse
  }

  def isPerfectSqr(n:Long):Boolean = {
    if(n <= 0 ) false
    else {
      val tst = Math.sqrt(n).toInt
      tst*tst == n
    }
  }

  def isTriangular(n:Long) = isPerfectSqr(8*n+1)
}
