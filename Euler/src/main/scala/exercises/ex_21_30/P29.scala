package exercises.ex_21_30

import com.gabbi.profiling.PerformanceComputation._

import scala.collection.immutable.Range.Inclusive

/**
 * Created by gabbi on 16/08/14.
 */
object P29 extends App {

  def distinctPowers(r: Inclusive):Int =  {
    val range = r.toSet

    val seq = for {
      a <- range
      b <- range
    } yield math.pow(a, b)

    seq.size
  }

  println(getStats(distinctPowers(2 to 100), 10))

}
