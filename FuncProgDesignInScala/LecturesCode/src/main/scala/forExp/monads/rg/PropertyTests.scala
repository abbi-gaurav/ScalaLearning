package forExp.monads.rg

/**
  * Created by gabbi on 29/05/16.
  */
object PropertyTests {
  def test[T](g: RGenerator[T], numTimes: Int = 100)(test: T => Boolean): Unit = {
    for (i <- 0 until numTimes) {
      val value = g.generate
      assert(test(value), s"test failed for $value")
    }
    println(s"passed $numTimes tests")
  }
}
