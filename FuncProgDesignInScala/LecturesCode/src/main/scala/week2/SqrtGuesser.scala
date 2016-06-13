package week2

/**
  * Created by gabbi on 05/06/16.
  */
object SqrtGuesser {
  def sqrtStream(x: Double): Stream[Double] = {
    def improve(guess: Double): Double = (guess + x / guess) / 2
    lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
    guesses
  }

  def isGoodEnough(guess: Double, x: Double): Boolean = math.abs((guess * guess - x) / x) < 0.0001

  def sqrt(x: Double): Double = sqrtStream(x) filter (isGoodEnough(_, x)) head
}
