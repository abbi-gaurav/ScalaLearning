package exercises.ex_21_30

/**
 * Created by gabbi on 04/08/14.
 * Euler discovered the remarkable quadratic formula:

n² + n + 41

It turns out that the formula will produce 40 primes for the consecutive values n = 0 to 39. However, when n = 40, 402 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when n = 41, 41² + 41 + 41 is clearly divisible by 41.

The incredible formula  n² − 79n + 1601 was discovered, which produces 80 primes for the consecutive values n = 0 to 79.
The product of the coefficients, −79 and 1601, is −126479.

Considering quadratics of the form:

n² + an + b, where |a| < 1000 and |b| < 1000

where |n| is the modulus/absolute value of n
e.g. |11| = 11 and |−4| = 4
Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of primes for consecutive values of n,
starting with n = 0.
 */
object P27 extends App {

  lazy val ps: Stream[Int] = 2 #:: Stream.from(3).filter(i => ps.takeWhile(j => j * j <= i).forall(i % _ > 0))

  def isPrime(x: Int): Boolean = {
    def loop(runningPrimes: Stream[Int]): Boolean = runningPrimes match {
      case h #:: rest => {
        if (x == h) true
        else if (h > x) false
        else loop(rest)
      }
    }
    loop(ps)
  }

  val bs = (-999 to 999).filter(j => isPrime(math.abs(j)))
  val as = (-999 to 999).filter(j => math.abs(j) % 2 > 0 || math.abs(j) == 2)

  val primeSeq = for{
    b <- bs
    a <- as
  }yield{
    (a*b, Stream.from(0).map(n => n*n + a*n +b).takeWhile(isPrime))
  }
  val t0 = System.nanoTime()
  val prod = primeSeq.maxBy(_._2.size)
  val t1 = System.nanoTime()
  println(prod + "in "+((t1-t0)/1000000000.0))

}
