package exercises.ex_41_50

import com.gabbi.profiling.PerformanceComputation
import exercises.utils.{MathOps, StringOps}

/**
 * Created by gabbi on 04/01/15.
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle number
 * s are: 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * By converting each letter in a word to a number corresponding to its alphabetical position and adding
 * these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10.
 * If the word value is a triangle number then we shall call the word a triangle word.
 * Using words.txt (right click and 'Save Link/Target As...'), a 16K text file
 * containing nearly two-thousand common English words, how many are triangle words?
 */
object Ex42 extends App{
  val fileData = scala.io.Source.fromFile("/Users/gabbi/learning/coursera/scala/intelliJIdeaWS/Euler/src/main/resources/p042_words.txt").mkString
  def countTriangularWords() = {
    fileData.split(",").foldLeft(0){
      case (acc, word) =>
        val w = word.replace("\"","")
        if(StringOps.isTriangularWord(w)) acc+1 else acc
    }
  }

  PerformanceComputation.publishPerformanceData(countTriangularWords())
}
