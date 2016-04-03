package codility.lessons.payon

/**
 * Created by gabbi on 29/08/14.
 */
object ReversePolishCalc extends App {
  def solution(S: String): Int = {

    // write your code in Scala 2.10
    try {
      val characters = S.toCharArray
      val res = characters.foldLeft(List[Int]()) {
        case (x :: y :: zs, '*') => x * y :: zs
        case (x :: y :: zs, '+') => x + y :: zs
        case (x :: y :: zs, '/') => x / y :: zs
        case (list, chr) => Integer.parseInt(chr.toString) :: list
      }
      if (res.size > 1 || res.size < 1) -1 else res.head
    } catch {
      case e: NumberFormatException => -1
      case e: Exception => -1
    }
  }

  println(solution("13+62*7+*"))

}
