package com.algos.sorting

/**
 * Created by gabbi on 13/09/14.
 */
object MergeSort {
  def sort[T <% Ordered[T]](list:List[T]):List[T] = {
    def merge(xs:List[T], ys:List[T]):List[T] = (xs, ys) match {
      case (_,Nil) => xs
      case (Nil, _) => ys
      case (x::xs1, y::ys1) =>
        if(x < y) x :: merge(xs1, ys) else y :: merge(xs, ys1)
    }

    val n = list.length/2
    if(n == 0) list
    else {
      val (fst, snd) = list splitAt n
      merge(sort(fst), sort(snd))
    }
  }
}
