package books.progInScala.ch10

import org.scalatest.FunSuite

/**
 * Created by gabbi on 14/07/14.
 */
class ElementalSuite extends FunSuite {
  test("elem should have passed width") {
    val ele = Element('e', 2, 3)
    assert(ele.width == 2)
  }

  test("elem should have passed height") {
    val ele = Element('e', 2, 3)
    assert(ele.height == 3)
  }
}
