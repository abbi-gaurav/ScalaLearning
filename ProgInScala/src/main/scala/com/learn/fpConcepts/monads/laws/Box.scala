package com.learn.fpConcepts.monads.laws

/**
 * Created by gabbi on 16/08/14.
 */
case class Box[+A](private val x: A) extends Container[A] {

  override def flatMap[B](f: A => Container[B]): Container[B] = {
    f(x)
  }

  override def toString = "Box(" + x.toString + ")"
}

case object Empty extends Container[Nothing] {
  override def flatMap[B](f: (Nothing) => Container[B]): Container[B] = {
    this
  }
}
