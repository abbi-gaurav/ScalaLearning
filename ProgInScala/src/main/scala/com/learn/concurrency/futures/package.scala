package com.learn.concurrency

import com.learn.concurrency.utils.Logger

import scala.concurrent.Future
import scala.util.control.NonFatal
import scala.util.{Failure, Success}

/**
 * Created by gabbi on 19/01/15.
 */
package object futures {
  import scala.concurrent.ExecutionContext.Implicits.global

  def work[I, T, O](input: I, f: I => Future[T])(succ: T => O): Unit = {
    workWithFuture(f(input))(succ)
  }

  def workWithFuture[O, T](f1: Future[T])(succ: T => O): Unit = {
    f1 onComplete {
      case Success(value) => Logger.debug(s"successfully transformed: ${succ(value)}")
      case Failure(NonFatal(e)) => Logger.error("error in future", e)
    }
  }

  def logFuture[T](f1: Future[T]):Unit = workWithFuture(f1)(identity)
}
