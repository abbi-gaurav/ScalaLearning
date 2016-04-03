package codility.lessons.payon

/**
 * Created by gabbi on 29/08/14.
 */
object FindWholeSquares {
  def solution(A: Int, B: Int): Int = {
    def isWholeSquare(n:Int):Boolean = {
      if(n == 1) true
      else {
        val upperLimit = (2 to n).find(i => i * i > n).get
        (2 to upperLimit - 1).exists(x => x * x == n)
      }
    }
    (A to B).filter(x => x > 0).count(isWholeSquare)
  }
}
