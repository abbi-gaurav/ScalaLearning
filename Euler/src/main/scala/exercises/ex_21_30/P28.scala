package exercises.ex_21_30

/**
 * Created by gabbi on 15/08/14.
 * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

21 22 23 24 25
20  7  8  9 10
19  6  1  2 11
18  5  4  3 12
17 16 15 14 13

It can be verified that the sum of the numbers on the diagonals is 101.

What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
 */
object P28 extends App{
  def m1(n:Int) = 1 + (1 to n).map(x => 16 * x * x + 4 * x + 4 ).foldLeft(BigInt(0)) (_ + _)

  println(m1(500))

  def m2(n:Int):BigInt = n match {
    case 0 => BigInt(1)
    case _ => 16 * n * n + 4 * n + 4 + m2(n-1)
  }
  println(m2(500))
}
