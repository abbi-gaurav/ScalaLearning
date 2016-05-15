package com.learn.fpConcepts.errorHandling

/**
  * Created by gabbi on 15/05/16.
  */
object OptionLifters {

  def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f

  def map2[A, B, C](aOpt: Option[A], bOpt: Option[B])(f: (A, B) => C): Option[C] = for {
    a <- aOpt
    b <- bOpt
  } yield f(a, b)

  def sequence[A](as: List[Option[A]]): Option[List[A]] = as match {
    case Nil => Option(Nil)
    case aOpt :: tail => for {
      a <- aOpt
      rest: List[A] <- sequence(tail)
    } yield a :: rest
  }

  def sequenceRec[A](as: List[Option[A]]): Option[List[A]] = {
    def loop(current: List[Option[A]], acc: List[A]): Option[List[A]] = current match {
      case Nil => Option(acc)
      case x :: xs => x match {
        case None => None
        case Some(a) => loop(xs, a :: acc)
      }
    }
    loop(as, Nil)
  }

  def sequence2[A](as: List[Option[A]]): Option[List[A]] = {
    as.foldRight[Option[List[A]]](Option(Nil)) { case (x, acc) => map2(x, acc)(_ :: _) }
  }

  def traverse1[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = sequence(a map f)

  def traverseRec[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    def loop(current: List[A], acc: List[B]): Option[List[B]] = current match {
      case Nil => Option(acc)
      case x :: xs => f(x) match {
        case None => None
        case Some(b) => loop(xs, b :: acc)
      }
    }
    loop(a, Nil)
  }

  def traverseFR[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = {
    a.foldRight[Option[List[B]]](Option(Nil)) { case (x, acc) => map2(f(x), acc)(_ :: _) }
  }

  def sequenceUsingTraverse[A](as: List[Option[A]]): Option[List[A]] = traverseRec(as)(identity)

  def traverse2[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match {
    case Nil => Option(Nil)
    case x :: xs => for {
      b <- f(x)
      rest <- traverse2(xs)(f)
    } yield b :: rest
  }
}
