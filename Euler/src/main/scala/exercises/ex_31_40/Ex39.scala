package exercises.ex_31_40

/**
 * Created by gabbi on 07/10/14.
 * If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

{20,48,52}, {24,45,51}, {30,40,50}

For which value of p ≤ 1000, is the number of solutions maximised?
 */
object Ex39 extends App {
  def solution = {
    def numSolutions(p: Int) = (1 to p / 3).count { a =>
      (p * p - (2 * p * a)) % (2 * (p - a)) == 0
    }

    (5 to 1000).view.maxBy(numSolutions)
  }

  import com.gabbi.profiling.PerformanceComputation._

  println(getStats(solution))
}
