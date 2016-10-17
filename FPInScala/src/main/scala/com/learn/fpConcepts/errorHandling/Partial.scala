package com.learn.fpConcepts.errorHandling

/**
  * Created by gabbi on 16/05/16.
  */
sealed trait Partial[+E, +A] {
  def flatMap[EE >: E, B](f: A => Partial[EE, B]): Partial[EE, B] = this match {
    case Error(seq) => Error(seq)
    case Success(a) => f(a)
  }

  def map[B](f: A => B): Partial[E, B] = this.flatMap(a => Success(f(a)))

  def map2[EE >: E, B, C](b: Partial[EE, B])(f: (A, B) => C): Partial[EE, C]

  def orElse[EE >: E, B >: A](b: => Partial[EE, B]) = this match {
    case Error(seq) => b
    case _ => this
  }
}

object Partial {
  def traverse[E, A, B](list: List[Partial[E, A]])(f: A => Partial[E, B]): Partial[E, List[B]] = {
    list.foldRight[Partial[E, List[B]]](Success(Nil)) { case (x: Partial[E, A], acc: Partial[E, List[B]]) =>
      x.flatMap(f).map2(acc)((f: B, s: List[B]) => f :: s)
    }
  }

  def sequence[E, A](list: List[Partial[E, A]]): Partial[E, List[A]] = traverse(list)(x => Success(x))
}

case class Error[+E](get: Seq[E]) extends Partial[E, Nothing] {
  override def map2[EE >: E, B, C](b: Partial[EE, B])(f: (Nothing, B) => C): Partial[EE, C] = b match {
    case Error(seqb) => Error(get ++ seqb)
    case _ => this
  }
}

case class Success[+A](get: A) extends Partial[Nothing, A] {
  override def map2[EE >: Nothing, B, C](b: Partial[EE, B])(f: (A, B) => C): Partial[EE, C] = b match {
    case Success(bVal) => Success(f(get, bVal))
    case Error(seq) => Error(seq)
  }
}

