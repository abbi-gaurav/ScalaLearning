package com.learn.fpConcepts.laziness

import com.learn.fpConcepts.laziness.StreamOps._

/**
  * Created by gabbi on 05/07/16.
  */
object StreamUtils {
  def hasSubSeq[A](stream: Stream[A], sub: Stream[A]): Boolean = {
    def loop(c: Stream[A]): Boolean = c match {
      case x #:: xs => startsWith(c, sub) || loop(xs)
      case _ => false
    }
    loop(stream)
  }

  def uHasSubSeq[A](stream: Stream[A], sub: Stream[A]) = stream.uTails wExists (startsWith(_, sub))

  def startsWith[A](stream: Stream[A], sub: Stream[A]): Boolean = {
    stream.uZipAll(sub).uTakeWhile(_._2.isDefined).forall { case (f, s) => f == s }
  }
}
