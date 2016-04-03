package com.ds.queues

/**
 * Created by gabbi on 14/09/14.
 */
class Queue[+A](in: List[A] = Nil, out: List[A] = Nil) {
  def enqueue[B >: A](x: B): Queue[B] = new Queue(x :: in, out)

  def dequeue: (A, Queue[A]) = out match {
    case x :: xs => (x, new Queue[A](in, xs))
    case _ => in match {
      case y :: ys =>
        val outQ = in.reverse
        (outQ.head, new Queue[A](Nil, outQ.tail))
      case Nil => throw new NoSuchElementException("Empty.queue")
    }
  }

  override def toString = s"[ ${in mkString " "} ${out.reverse mkString " "} ]"

  def isEmpty: Boolean = (in, out) match {
    case (Nil, Nil) => true
    case (_, _) => false
  }
}

object Queue {
  def empty[A]: Queue[A] = new Queue()

  def apply[A](xs: A*) =
    xs.foldLeft(Queue.empty[A]) { case (acc, x) => acc.enqueue(x) }
}
