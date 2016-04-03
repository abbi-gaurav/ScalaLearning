package books.progInScala.ch10

/**
 * Created by gabbi on 02/07/14.
 */

abstract class Element {

  def contents: Array[String]

  def width: Int = contents(0).length

  def height: Int = contents.length


  def above(that: Element) = {
    val this1 = this widen that.width
    val that1 = that widen this.width
    assert(this1.width == that1.width)
    Element(this1.contents ++ that1.contents)
  }

  def beside(that: Element) = {
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    assert(this1.height == that1.height)

    Element(
      for ((line1, line2) <- this1.contents zip that1.contents)
      yield line1 + line2)
  }

  private def widen(w: Int): Element = {
    if (w <= width) this
    else {
      val left = Element(' ', (w - width) / 2, height)
      val right = Element(' ', w - width - left.width, height)
      left beside this beside right
    }
  }

  def heighten(h: Int): Element = {
    if (h <= height) this
    else {
      val top = Element(' ', width, (h - height) / 2)
      val bottom = Element(' ', width, h - height - top.height)
      top above this above bottom
    }
  }

  override def toString = contents mkString "\n"

}


object Element {

  private class ArrayElement(val contents: Array[String]) extends Element

  private class LineElement(s: String) extends Element {
    val contents = Array(s)

    override def width = s.length

    override def height = 1
  }

  private class UniformElement(ch: Char, override val width: Int, override val height: Int) extends Element {
    private val line = ch.toString * width

    override def contents: Array[String] = Array.fill(height)(line)
  }

  def apply(contents: Array[String]): Element = new ArrayElement(contents)

  def apply(s: String): Element = new LineElement(s)

  def apply(ch: Char, width: Int, height: Int): Element = {
    require(width > 0 && height > 0)
    new UniformElement(ch, width, height)
  }
}
