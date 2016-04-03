package books.progInScala.ch28

/**
 * Created by gabbi on 11/10/14.
 */
trait Tree[+T] {
  def elem: T

  def left: Tree[T]

  def right: Tree[T]
}

object Empty extends Tree[Nothing] {
  override def elem: Nothing = fail

  override def left: Tree[Nothing] = fail

  override def right: Tree[Nothing] = fail

  private def fail = throw new IllegalStateException("empty tree")
}

class Branch[+T](val elem: T, val left: Tree[T], val right: Tree[T]) extends Tree[T] {
  def canEquals(other: Any) = other match {
    case that: Branch[_] => true
    case _ => false
  }

  override def equals(other:Any):Boolean = other match {
    case that:Branch[_] => (that canEquals this) && elem == that.elem && left == that.left && right == that.right
    case _ => false
  }

  override def hashCode(): Int = 41 * (41 * (41 + elem.hashCode) + left.hashCode) + right.hashCode
}
