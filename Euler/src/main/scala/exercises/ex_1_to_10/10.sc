/*
problem 10
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.
 */

lazy val ps: Stream[Int] = 2 #:: Stream.from(3).filter { i =>
  ps.takeWhile(j => j * j <= i).forall(i % _ > 0)
}

val limit: Int = 2000000
//for integer overflow changing to Long, hence foldLeft
ps.takeWhile(_ < limit).foldLeft(0L)(_ + _)
