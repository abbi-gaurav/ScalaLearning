package week2

/**
  * Created by gabbi on 05/06/16.
  */
object Streams {
  lazy val primes: Stream[Int] = sieve(Stream.from(2))

  def sieve(s: Stream[Int]): Stream[Int] = {
    s.head #:: sieve(s.tail filter (_ % s.head != 0))
  }
}
