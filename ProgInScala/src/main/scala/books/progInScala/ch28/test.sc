import books.progInScala.ch28.Color
import scala.collection.immutable.HashSet
class Point(val x: Int, val y: Int) {
  override def equals(other: Any): Boolean = other match {
    case that: Point => (that canEqual this) && that.x == x && that.y == y
    case _ => false
  }

  override def hashCode: Int = 41 * (41 + x) + y

  def canEqual(other: Any) = other.isInstanceOf[Point]
}

class ColoredPoint(x: Int, y: Int, val color: Color.Value) extends Point(x, y) {
  override def equals(other: Any): Boolean = other match {
    case that: ColoredPoint =>
      //only that canEqual this to make sure both are of same type,
      // not in the type chain
      (that canEqual this) && this.color == that.color && super.equals(that)
    case _ => false
  }

  override def hashCode = 41 * super.hashCode + color.hashCode

  override def canEqual(other: Any) = other.isInstanceOf[ColoredPoint]
}
val p1 = new Point(2, 3)
val p2 = new Point(2, 3)
p1 equals p2
p1 == p2
//this is reference comparison
p1 eq p2
val set = HashSet(p1)
set contains p2
val p3 = new ColoredPoint(2, 3, Color.Blue)
val p4 = new Point(2, 1) {
  override val y = 3
}
p2 == p4
p1 == p4
val b = p4 == p3
set contains p4

