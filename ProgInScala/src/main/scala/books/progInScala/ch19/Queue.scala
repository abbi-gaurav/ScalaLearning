package books.progInScala.ch19

/**
 * Created by gabbi on 03/08/14.
 */
trait Queue[+T] {
  def head: T

  def tail: Queue[T]

  def append[U >: T](x: U): Queue[U]
}

object Queue {
  def apply[T](xs: T*):Queue[T] = new QueueImpl[T](xs.toList.reverse, Nil)

  private class QueueImpl[+T](private val leading: List[T], private val trailing: List[T]) extends Queue[T] {
    private def mirror = {
      if (leading.isEmpty) new QueueImpl[T](trailing.reverse, Nil)
      else this
    }

    def head = mirror.leading.head

    def tail = {
      val q = mirror
      new QueueImpl[T](q.leading.tail, q.trailing)
    }

    def append[U >: T](x: U) = new QueueImpl[U](leading, x :: trailing)
  }

}
