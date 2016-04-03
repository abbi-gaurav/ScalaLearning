//problem 7
/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10 001st prime number?
 * @param stream
 * @return
 */
def primeNumbers(stream:Stream[Int]):Stream[Int] = {
  stream.head #:: primeNumbers(stream.tail.filter(_ % stream.head > 0))
}


def primes = primeNumbers(Stream.from(2))
primes.take(10).toList
//primes(1)

//this works faster
lazy val ps:Stream[Int] = 2 #:: Stream.from(3).
                                   filter(i => ps.takeWhile(j => j*j <= i).forall(i % _ > 0))
ps(10000)

