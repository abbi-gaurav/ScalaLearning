package codility.lessons.one;

/**
 * Created by gabbi on 16/09/14.
 */
object EasyLessons {
  def solution(A: Array[Int]): Int = {
    var rightSum = A.sum
    var currentIdx = 1
    var leftSum = 0

    var minDiff = Int.MaxValue
    var minIdx = currentIdx

    while(currentIdx < A.length){
      leftSum = leftSum+A(currentIdx-1)
      rightSum = rightSum - A(currentIdx-1)

      val newDiff = math.abs(rightSum - leftSum)
      if(newDiff < minDiff){
        minDiff = newDiff
        minIdx = currentIdx
      }

      currentIdx = currentIdx+1

    }
    minDiff
  }
}
