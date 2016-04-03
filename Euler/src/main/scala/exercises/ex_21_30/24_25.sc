//problem 24
/**
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible
 * permutation of the digits 1, 2, 3 and 4.
 * If all of the permutations are listed numerically or alphabetically, we call it
 * lexicographic order.
 * The lexicographic permutations of 0, 1 and 2 are: 012   021   102   120   201   210
 * What is the millionth lexicographic permutation of the digits
 * 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
def permutations[T](list: List[T]): List[List[T]] = list match {
  case Nil => List(Nil)
  case _ => for {
    i <- list.indices.toList
    (before, rest) = list.splitAt(i)
    elem = rest.head
    subPermutate <- permutations(before ++ rest.tail)
  } yield elem :: subPermutate
}
permutations((0 to 2).toList).map(xs => xs mkString " ").mkString("\n")
(0 to 9).permutations.drop(999999).next.mkString
//problem 25
/**
 * The Fibonacci sequence is defined by the recurrence relation:
Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
Hence the first 12 terms will be:

F1 = 1
F2 = 1
F3 = 2
F4 = 3
F5 = 5
F6 = 8
F7 = 13
F8 = 21
F9 = 34
F10 = 55
F11 = 89
F12 = 144
The 12th term, F12, is the first term to contain three digits.

What is the first term in the Fibonacci sequence to contain 1000 digits?
 */

lazy val fib: Stream[BigInt] = BigInt(1) #:: BigInt(1) #:: fib.zip(fib.tail).map { case (x, y) => x + y}
def termThatContains(numDigits:Int) = fib.view.takeWhile(_.toString.length < numDigits).size + 1

termThatContains(3)
termThatContains(1000)
