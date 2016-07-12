package com.learn.fpConcepts.errorHandling

/**
  * Created by gabbi on 15/05/16.
  */
object EitherOps {

  class EitherExtRightP[E, A](either: Either[E, A]) {
    def map[B](f: A => B): Either[E, B] = either.right.map(f)

    def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = either.right.flatMap(f)

    def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = either match {
      case Left(_) => b
      case Right(r) => Right(r)
    }

    def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = either match {
      case Left(e) => Left(e)
      case Right(a) => b match {
        case Left(e) => Left(e)
        case Right(bVal) => Right(f(a, bVal))
      }
    }
  }

  implicit def extendedEither[E, A](either: Either[E, A]): EitherExtRightP[E, A] = new EitherExtRightP(either)

  def traverse[E, A, B](as: List[Either[E, A]])(f: A => Either[E, B]): Either[E, List[B]] = {
    as.foldRight[Either[E, List[B]]](Right(Nil)) { case (x: Either[E, A], acc: Either[E, List[B]]) =>
      x.flatMap(f).map2(acc)((x: B, y: List[B]) => x :: y)
    }
  }

  def sequence[E, A](as: List[Either[E, A]]): Either[E, List[A]] = traverse(as)((x: A) => Right(x))
}
