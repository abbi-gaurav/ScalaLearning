package exercises.ex_31_40

/**
 * Created by gabbi on 23/09/14.
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right,
 * and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
object Ex37 extends App {

  import exercises.utils.MathOps._

  def isTrunctable(x: Int) = {
    def loop(init: List[Int], last: List[Int]): Boolean = (init, last) match {
      case (List(l), List(r)) => isPrime(l) && isPrime(r)
      case (_, _) => if (!isPrime(list2Long(init)) || !isPrime(list2Long(last))) false else loop(init.init, last.tail)
    }
    if (x <= 7) false
    else {
      val listNums = asDigits(x)
      loop(listNums.init, listNums.tail)
    }
  }

  def trunctablePrimesSum:Int = {
    ps.drop(4).filter(isTrunctable).take(11).sum
  }

  import com.gabbi.profiling.PerformanceComputation._

  publishPerformanceData(trunctablePrimesSum)

}
