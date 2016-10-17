package com.learn.fpConcepts.hof.fds

import scala.annotation.tailrec

/**
  * Created by gabbi on 04/04/16.
  */
object IsSorted extends App {

  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
    @tailrec
    def go(start: Int, end: Int): Boolean = {
      if (start >= end) true
      else if (gt(as(start), as(start + 1))) false
      else go(start + 1, end)
    }
    go(0, as.length - 1)
  }

  println(isSorted(Array(0, 1, 2, 4, 8), (x: Int, y: Int) => x > y))
  println(isSorted(Array(0, 1, 1, 7, 2, 4, 8, 9), (x: Int, y: Int) => x > y))
}
