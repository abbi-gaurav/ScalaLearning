package books.progInScala.ch10

/**
 * Created by gabbi on 06/07/14.
 */

import books.progInScala.ch10.Element._

object Spiral {
  val space = apply(" ")
  val corner = apply("+")

  def spiral(nEdges: Int, direction: Int): Element = {
    if (nEdges == 1)
      apply("+")
    else {
      val sp = spiral(nEdges - 1, (direction + 3) % 4)
      def verticalBar = apply('|', 1, sp.height )
      def horizontalBar = apply('-', sp.width, 1)
      if (direction == 0)
        (corner beside horizontalBar) above (sp beside space)
      else if (direction == 1)
        (sp above space) beside (corner above verticalBar)
      else if (direction == 2)
        (space beside sp) above (horizontalBar beside corner)
      else
        (verticalBar above corner) beside (space above sp)
    }
  }

  def main(args: Array[String]) {
    val nSides = args(0).toInt
    println(spiral(nSides, 0))
  }
}
