package com.learn.fpConcepts.laziness

/**
  * Created by gabbi on 16/05/16.
  */
object StreamOps {

  def to[A](stream: Stream[A]): List[A] = stream match {
    case Stream() => Nil
    case h #:: tl => h :: to(tl)
  }

  def to2[A](stream: Stream[A]): List[A] = {
    stream.foldRight[List[A]](Nil) { case (x, acc) => x :: acc }
  }

  def take[A](stream: Stream[A], n: Int): Stream[A] = stream match {
    case Stream() => Stream.empty
    case h #:: tail if n > 0 => h #:: take(tail, n - 1)
    case _ => Stream.empty
  }
}
