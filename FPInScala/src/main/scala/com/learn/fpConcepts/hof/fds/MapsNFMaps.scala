package com.learn.fpConcepts.hof.fds

/**
  * Created by gabbi on 28/04/16.
  */
object MapsNFMaps {
  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = l match {
    case Nil => Nil
    case x :: xs => f(x) ::: flatMap(xs)(f)
  }

  def filterUsingFM[A](l: List[A])(p: A => Boolean): List[A] = flatMap(l)(a => if (p(a)) List(a) else Nil)

  def zipWith[A, B, C](l1: List[A], l2: List[B])(f: (A, B) => C): List[C] = (l1, l2) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (x :: xs, y :: ys) => f(x, y) :: zipWith(xs, ys)(f)
  }
}
