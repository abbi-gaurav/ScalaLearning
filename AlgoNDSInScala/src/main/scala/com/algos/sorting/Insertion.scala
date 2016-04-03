package com.algos.sorting

/**
 * Created by gabbi on 13/09/14.
 */
object Insertion {

  def sort[T <% Ordered[T]](list: List[T]): List[T] = {
    def sort(src: List[T], dst: List[T]): List[T] = src match {
      case x :: xs => sort(xs, insert(x, dst))
      case Nil => dst
    }

    def insert(x: T, dst: List[T]): List[T] = dst match {
      case h :: t if x > h => h :: insert(x, t)
      case _ => x :: dst
    }

    sort(list, Nil)
  }
}
