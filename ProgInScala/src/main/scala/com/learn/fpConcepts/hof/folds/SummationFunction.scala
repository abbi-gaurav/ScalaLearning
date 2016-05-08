package com.learn.fpConcepts.hof.folds

import scala.annotation.tailrec

/**
  * Created by gabbi on 03/01/16.
  * f(a,b)(g) =  x = (a to b) ==> ∑g(x)
  */
object SummationFunction {
  def summateTailRec(a: Int, b: Int)(g: Int => Int): Int = {
    @tailrec
    def loop(x: Int, y: Int, res: Int): Int = {
      if (x > y) res
      else loop(x + 1, y, res + g(x))
    }
    loop(a, b, 0)
  }

  def summateFoldLeft(a: Int, b: Int)(g: Int => Int): Int = (a to b).foldLeft(0) { case (acc, x) => acc + g(x) }

  def summateFoldRight(a: Int, b: Int)(g: Int => Int): Int = (a to b).foldRight(0) { case (x, acc) => acc + g(x) }


}
