package com.learn.fpConcepts.laziness

import scala.language.implicitConversions

/**
  * Created by gabbi on 16/05/16.
  */
object StreamOps {

  class StreamWrapper[A](stream: Stream[A]) {
    def toList: List[A] = stream match {
      case Stream() => Nil
      case h #:: tl => h :: tl.toList
    }

    def toList2: List[A] = {
      stream.foldRight[List[A]](Nil) { case (x, acc) => x :: acc }
    }

    def takeMine(n: Int): Stream[A] = stream match {
      case Stream() => Stream.empty
      case h #:: tail if n > 0 => h #:: tail.takeMine(n - 1)
      case _ => Stream.empty
    }

    def wFoldRight[B](z: => B)(f: (A, => B) => B): B = stream match {
      case Stream() => z
      case h #:: tl => f(h, tl.wFoldRight(z)(f))
    }

    def wExists(p: A => Boolean) = stream.wFoldRight(false)((a, acc) => p(a) || acc)

    //stream.wFoldRight(false)((a, acc) => !(!p(a) || !acc))
    //!stream.wExists(x => !p(x))
    def wForAll(p: A => Boolean) = stream.wFoldRight(true)((e, acc) => p(e) && acc)

    //case pattern match does not work with call by name
    def wTakeWhile(p: A => Boolean): Stream[A] = stream.wFoldRight[Stream[A]](Stream.empty) { (e, acc) =>
      if (p(e)) e #:: acc else Stream.empty
    }

    def wMap[B](f: A => B): Stream[B] = stream.wFoldRight(Stream.empty[B])((a, acc) => f(a) #:: acc)

    def uMap[B](f: A => B): Stream[B] = unfold(stream) {
      case x #:: xs => Some((f(x), xs))
      case _ => None
    }

    def uTake(n: Int): Stream[A] = unfold((stream, n)) { tuple =>
      val (cs, cn) = tuple
      cs match {
        case x #:: xs if cn > 0 => Some(x, (xs, cn - 1))
        case _ => None
      }
    }

    def uTakeWhile(p: A => Boolean): Stream[A] = unfold(stream) {
      case x #:: xs if p(x) => Some(x, xs)
      case _ => None
    }

    def uZipWith[B, C](that: Stream[B])(f: (A, B) => C): Stream[C] = unfold((stream, that)) {
      case ((a #:: as), (b #:: bs)) => Some(f(a, b), (as, bs))
      case _ => None
    }

    def uZip[B](second: Stream[B]): Stream[(A, B)] = uZipWith(second)((_, _))

    def uZipWithAll[B, C](that: Stream[B])(f: (Option[A], Option[B]) => C): Stream[C] = unfold((stream, that)) {
      case (Stream(), Stream()) => None
      case (a #:: as, Stream()) => Some(f(Some(a), None), (as, Stream.empty))
      case (Stream(), b #:: bs) => Some(f(None, Some(b)), (Stream.empty, bs))
      case ((a #:: as), (b #:: bs)) => Some(f(Some(a), Some(b)), (as, bs))
    }

    def uZipAll[B](that: Stream[B]): Stream[(Option[A], Option[B])] = uZipWithAll(that)((_, _))

    def uTails: Stream[Stream[A]] = unfold(stream) {
      case org@(x #:: xs) => Some(org, xs)
      case _ => None
    } wAppend Stream(Stream.empty)

    def wFilter(p: A => Boolean): Stream[A] = {
      stream.wFoldRight(Stream.empty[A])((a, acc) => if (p(a)) a #:: acc else acc)
    }

    def wAppend(stream2: => Stream[A]) = stream.wFoldRight(stream2)((a, acc) => a #:: acc)

    def wFlatMap[B](f: A => Stream[B]): Stream[B] = stream.wFoldRight(Stream.empty[B]) { (a, acc) =>
      f(a).wAppend(acc)
    }

  }

  implicit def extendedStream[A](stream: Stream[A]): StreamWrapper[A] = new StreamWrapper[A](stream = stream)

  def unfold[A, S](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((a, s)) => a #:: unfold(s)(f)
    case None => Stream.empty
  }

  def constant[A](a: A): Stream[A] = a #:: constant(a)

  def from2(n: Int): Stream[Int] = n #:: from2(n + 1)

  def fibs: Stream[Int] = {
    def loop(a: Int, b: Int): Stream[Int] = a #:: loop(b, a + b)
    loop(0, 1)
  }

  def uFibs: Stream[Int] = unfold((0, 1)) { (tuple) =>
    val (a, b) = tuple
    Some(a, (b, a + b))
  }

  def uFrom(n: Int): Stream[Int] = unfold(n) { a => Some(a, a + 1) }

  def uConstant(n: Int): Stream[Int] = unfold(n) { a => Some(a, a) }

}
