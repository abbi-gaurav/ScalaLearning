package exercises.ex_41_50

import com.gabbi.profiling.PerformanceComputation

import scala.concurrent.duration.Duration

/**
 * Created by gabbi on 17/01/15.
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in
 * some order, but it also has a rather interesting sub-string divisibility property.
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
 * d2d3d4=406 is divisible by 2
 * d3d4d5=063 is divisible by 3
 * d4d5d6=635 is divisible by 5
 * d5d6d7=357 is divisible by 7
 * d6d7d8=572 is divisible by 11
 * d7d8d9=728 is divisible by 13
 * d8d9d10=289 is divisible by 17
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 **/
object Ex43 extends App {

  import exercises.utils.MathOps._

import scala.concurrent._

  private def divisible(list: List[Int]): Boolean = {
    val digitsTriplets = list.tail.sliding(3).toList
    (ps zip digitsTriplets).forall {
      case (prime, triplet) => list2Long(triplet) % prime == 0
    }
  }

  def sol1 = {
    List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).permutations.foldLeft(BigInt(0)) {
      case (acc, list) =>
        if (divisible(list)) acc + list2Long(list) else acc
    }
  }

  def sol2:Long = {
    val perms = List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).permutations
    import scala.concurrent.ExecutionContext.Implicits.global
    val futureRes = Future.traverse(perms){list =>
      Future{
        if(divisible(list)) list2Long(list) else 0L
      }
    }
    Await.result(futureRes, Duration.Inf).sum
  }

//  PerformanceComputation.publishPerformanceData(sol1)
  PerformanceComputation.publishPerformanceData(sol2)
  private class MyIterator(private var startVar: Long, private val end: Long) extends Iterator[Long] {
    override def hasNext: Boolean = startVar <= end

    override def next(): Long = {
      val ret = startVar
      startVar = startVar + 1L
      ret
    }
  }

}
