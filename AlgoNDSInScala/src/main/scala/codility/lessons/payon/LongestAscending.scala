package codility.lessons.payon

/**
 * Created by gabbi on 29/08/14.
 */
object LongestAscending {
  def index(A: Array[Int]): Int = {
    lengthAndIndex(A)._1
  }

  def longestAscendingSubSequence(A:Array[Int]):Array[Int] = {
    val(idx, length) = lengthAndIndex(A)
    A.slice(idx, idx+length)
  }

  private def lengthAndIndex(A: Array[Int]): (Int, Int) = {
    var idxForMax = 0
    var maxLength = 1

    var currIdx = 0
    var currLength = 1
    var i = 0

    while (i < A.length - 1) {
      if (A(i + 1) > A(i)) {
        currLength = currLength + 1
        i = i + 1
      } else {
        if (currLength > maxLength) {
          maxLength = currLength
          idxForMax = currIdx
        }
        i = i + 1
        currIdx = i
        currLength = 1
      }
    }
    (idxForMax, maxLength)
  }
}
