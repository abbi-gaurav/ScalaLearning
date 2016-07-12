package lazyEvaluation

/**
  * Created by gabbi on 05/06/16.
  */
sealed trait IntSet {
  def incl(x: Int): IntSet

  def contains(x: Int): Boolean

  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  override def incl(x: Int): IntSet = NonEmpty(elem = x, Empty, Empty)

  override def contains(x: Int): Boolean = false

  override def union(other: IntSet): IntSet = other
}

case class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def incl(x: Int): IntSet = {
    if (x < elem) left.incl(x)
    else if (x > elem) right.incl(x)
    else this
  }

  override def contains(x: Int): Boolean = {
    if (x < elem) left.contains(x)
    else if (x > elem) right.contains(x)
    else true
  }

  override def union(other: IntSet): IntSet = (left union (right union other)) incl elem
}