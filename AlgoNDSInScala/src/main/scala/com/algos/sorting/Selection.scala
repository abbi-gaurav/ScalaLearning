package com.algos.sorting

/**
 * Created by gabbi on 14/09/14.
 */
object Selection {
  def sort[T <% Ordered[T]](list: List[T]): List[T] = {
    def maximum(xs: List[T]): List[T] = xs.tail.foldLeft(List(xs.head)) {
      case (acc, x) => if (x > acc.head) x :: acc else acc.head :: x :: acc.tail
    }
    def helper(src: List[T], acc: List[T]): List[T] = src match {
      case Nil => acc
      case _ =>
        val listWithMaxAsHead = maximum(src)
        val (max, rest) = (listWithMaxAsHead.head, listWithMaxAsHead.tail)
        helper(rest, max :: acc)
    }
    helper(list, Nil)
  }
}
