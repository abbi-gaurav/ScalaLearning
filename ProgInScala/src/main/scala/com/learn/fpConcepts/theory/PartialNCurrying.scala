package com.learn.fpConcepts.theory

/**
  * Created by gabbi on 04/04/16.
  */
object PartialNCurrying {
  /**
    * takes a value and a function of two arguments, and returns a function of one argument as its result.
    */
  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = { (b: B) =>
    f(a, b)
  }

  //converts a function of N arguments into a function of one argument that returns another function as its result
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = { (a: A) =>
    val bToC: (B) => C = (b: B) => f(a, b)
    bToC
  }

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = { (a, b) =>
    val bToC: (B) => C = f(a)
    bToC(b)
  }
}
