package com.learn.fpConcepts.monads.examples

/**
 * Created by gabbi on 04/10/14.
 */
sealed trait Validation[E, A] {
  def map[B](f: A => B): Validation[E, B]

  def flatMap[B](f: A => Validation[E, B]): Validation[E, B]

  def liftFail[F](f: E => F): Validation[F, A]
}

case class Success[E, A](a: A) extends Validation[E, A] {
  override def map[B](f: (A) => B): Validation[E, B] = flatMap(x => Success(f(x)))

  override def flatMap[B](f: (A) => Validation[E, B]): Validation[E, B] = f(a)

  override def liftFail[F](f: (E) => F): Validation[F, A] = Success(a)
}

case class Failure[E, A](e: E) extends Validation[E, A] {
  override def map[B](f: (A) => B): Validation[E, B] = Failure(e)

  override def flatMap[B](f: (A) => Validation[E, B]): Validation[E, B] = Failure(e)

  override def liftFail[F](f: (E) => F): Validation[F, A] = Failure(f(e))
}
