package exercises.utils

/**
 * Created by gabbi on 23/09/14.
 */
object StringOps extends App{
  private val smallAInt = 'a'
  private val largeAInt = 'A'

  def isPalindrome(str: String): Boolean = {
    if (str.length <= 1) true
    else if (str.charAt(0) != str.charAt(str.length - 1)) false
    else isPalindrome(str.substring(1, str.length - 1))
  }

  def alphabeticalPos(c:Char) = (if(c.isLower) c - 'a' else c - 'A') + 1

  def sumOfAlphaPos(str:String) = str.foldLeft(0){
    case (acc,c) => alphabeticalPos(c) + acc
  }

  def isTriangularWord(str:String) = MathOps.isTriangular(sumOfAlphaPos(str))

  println(alphabeticalPos('K'))

  println(isTriangularWord("SKY"))
}
