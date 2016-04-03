package com.algos.sorting

/**
 * Created by gabbi on 13/09/14.
 */
object QuickSort {
  def sort[T](list:List[T])(implicit ord:Ordering[T]):List[T] = list match {
    case Nil => Nil
    case x :: xs =>
      val (before, after) = xs partition (ord.lt(_,x))
      sort(before) ::: x::sort(after)
  }
}
