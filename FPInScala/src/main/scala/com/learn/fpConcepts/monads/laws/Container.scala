package com.learn.fpConcepts.monads.laws


/**
 * Created by gabbi on 16/08/14.
 */
trait Container[+A] {

  final def map[B](f: A => B): Container[B] = {
    flatMap(x => Box(f(x)))
  }

  def flatMap[B](f: A => Container[B]): Container[B]

  def otherwise[B >: A](alternative: => Container[B]) = this match {
    case Empty => alternative
    case _ => this
  }

  def filter(p: A => Boolean): Container[A] = flatMap { x => if (p(x)) Box(x) else Empty}
}
