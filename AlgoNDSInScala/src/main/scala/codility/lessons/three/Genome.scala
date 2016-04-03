package codility.lessons.three

/**
 * Created by gabbi on 04/10/14.
 */
object Genome extends App {
  def solution(S: String, P: Array[Int], Q: Array[Int]): Array[Int] = {
    // write your code in Scala 2.10
    val genomeValues = Map('A' -> 1, 'C' -> 2, 'G' -> 3, 'T' -> 4)
    val toNumbers = S.map(x => genomeValues(x))
    (0 until P.length).map(x => toNumbers.slice(P(x), Q(x) + 1).min).toArray
  }

  def solution2(S: String, P: Array[Int], Q: Array[Int]): Array[Int] = {
    val prefixSumA = Array.fill(S.length + 1) {
      0
    }
    val prefixSumC = Array.fill(S.length + 1) {
      0
    }
    val prefixSumG = Array.fill(S.length + 1) {
      0
    }

    (0 until S.length) foreach { i =>
      val x = S(i)
      val a = if(x == 'A') 1 else 0
      val c = if(x == 'C') 1 else 0
      val g = if(x == 'G') 1 else 0

      prefixSumA(i + 1) = prefixSumA(i) + a
      prefixSumC(i + 1) = prefixSumC(i) + c
      prefixSumG(i + 1) = prefixSumG(i) + g
    }

    val res = Array.fill(P.length){0}
    (0 until P.length) foreach { i =>
      val from = P(i)+1
      val to = Q(i)+1

      if((prefixSumA(to) - prefixSumA(from-1)) > 0){
        res(i) = 1
      }else if((prefixSumC(to) - prefixSumC(from-1)) > 0){
        res(i) = 2
      }else if((prefixSumG(to) - prefixSumG(from-1)) > 0){
        res(i) = 3
      }else{
        res(i) = 4
      }
    }
    res
  }

  println(solution2("CAGCCTA", Array(2, 5, 0), Array(4, 5, 6)).toList)
}
