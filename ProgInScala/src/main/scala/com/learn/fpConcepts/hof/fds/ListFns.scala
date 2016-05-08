package com.learn.fpConcepts.hof.fds

/**
  * Created by gabbi on 30/04/16.
  */
object ListFns {
  def hasSubsequence[A](l: List[A], sub: List[A]): Boolean = {
    val subSize: Int = sub.size

    def loop(current: List[A]): Boolean = current match {
      case Nil => false
      case xs => xs.take(subSize) == sub || loop(xs.tail)
    }
    loop(l)
  }
}
