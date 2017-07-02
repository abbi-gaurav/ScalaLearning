package com.learn.fpConcepts.composition

/**
  * Created by gabbi on 10/04/16.
  */
object How2Compose {
  def compose[A, B, C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))
}
