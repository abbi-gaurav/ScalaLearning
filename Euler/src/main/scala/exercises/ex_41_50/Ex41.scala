package exercises.ex_41_50

/**
 * Created by gabbi on 04/01/15.
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once.
 * For example, 2143 is a 4-digit pandigital and is also prime.
 * What is the largest n-digit pandigital prime that exists?
 */
import exercises.utils.MathOps._
import com.gabbi.profiling.PerformanceComputation._
//use sum of digits concept
object Ex41 extends App{
  def findLargestPandigitalPrime():Int = {
    (7654321 to 1 by -1).find { x =>
      !(x%2 == 0) && !(x %3 == 0) && !(x%5 == 0) && isPrime(x) && isPandigital(x)
    }.get
  }

  publishPerformanceData(findLargestPandigitalPrime())
}
