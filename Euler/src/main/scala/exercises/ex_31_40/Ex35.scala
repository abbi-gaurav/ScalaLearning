package exercises.ex_31_40

/**
 * Created by gabbi on 22/09/14.
 */
import exercises.utils.MathOps._
import com.gabbi.profiling.PerformanceComputation._

object Ex35 extends App {
  lazy val primes: Stream[Int] = 2 #:: Stream.from(3).filter(x => primes.takeWhile(i => i * i <= x).forall(j => x % j > 0))

  def numOfCircularPrimes(limit: Int = 1000000) = {
    def isCircularPrime(prime: Int) = (!asDigits(prime).contains(0)) && rotations(prime).init.forall(isPrime)

    primes.takeWhile(limit >).count(isCircularPrime)
  }

  val stats= getStats(numOfCircularPrimes(), 10)

  println(stats)
}
