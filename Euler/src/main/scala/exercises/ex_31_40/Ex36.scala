package exercises.ex_31_40

/**
 * Created by gabbi on 23/09/14.
 * he decimal number, 585 = 1001001001 2 (binary), is palindromic in both bases
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 * (Please note that the palindromic number, in either base, may not include leading zeros.)
 */

import com.gabbi.profiling.PerformanceComputation._
import exercises.utils.StringOps._

object Ex36 extends App{
  def sum(limit:Int):Int = {
    (1 to limit).view.filter(x => isPalindrome(x.toString) && isPalindrome(x.toBinaryString)).sum
  }

  publishPerformanceData(sum(1000000))
}
