package exercises.ex_31_40

/**
 * Created by gabbi on 24/08/14.
 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
It is possible to make £2 in the following way:

1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
How many different ways can £2 be made using any number of coins?
 */
object Ex31 extends App {
  def numWays(sum: Int, list: List[Int]): Int = {
    if (sum == 0) 1
    else if (sum < 0 || list.isEmpty) 0
    else numWays(sum - list.head, list) + numWays(sum, list.tail)
  }

  def numWays2(list: List[Int], sum: Int): Int = list match {
    case x :: xs =>
      if (x > sum) 0
      else if (x == sum) 1
      else numWays2(list, sum - x) + numWays2(xs, sum)
    case _ => 0
  }

  val coins: List[Int] = List(1, 2, 5, 10, 20, 50, 100, 200)

  println(numWays2(coins, 200))

}
