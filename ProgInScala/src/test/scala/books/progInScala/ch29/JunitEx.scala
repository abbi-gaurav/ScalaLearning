package books.progInScala.ch29

import org.junit.Assert._
import org.junit.Test

/**
 * Created by gabbi on 18/10/14.
 */
class JunitEx {
  @Test
  def testMultiAdd(): Unit = {
    val s = Set() + 1 + 2 + 1 + 3 + 1
    assertEquals(3, s.size)
  }

  @Test
  def testList():Unit = {
    val s = (1 to 10).toList

    assertEquals(s, s.reverse.reverse)
  }
}
