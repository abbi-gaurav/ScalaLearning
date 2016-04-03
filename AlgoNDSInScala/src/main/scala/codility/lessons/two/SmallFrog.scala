package codility.lessons.two

/**
 * Created by gabbi on 19/09/14.
 */
object SmallFrog extends App {
  def solution(X: Int, A: Array[Int]): Int = {
    if(X > A.length+1) -1
    else {
      val testArray = Array.fill(A.length + 1) {
        false
      }

      def loop(idx: Int, acc: Int): Int = acc match {
        case 0 => idx - 1
        case _ => idx < A.length match {
          case false => -1
          case true => if (!testArray(A(idx))) {
            testArray(A(idx)) = true
            loop(idx + 1, acc - 1)
          } else {
            loop(idx + 1, acc)
          }
        }
      }
      loop(0, X)
    }
  }

  solution(5, Array(1, 3, 1, 4, 2, 3, 5, 4))
}
