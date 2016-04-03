package com.learn.concurrency.basics

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

/**
  * Created by gabbi on 23/10/14.
  */
object RetryTemplate {
  def retryFuture[T](numTries: Int)(body: => T): Future[T] = {
    def loop(attemptCount: Int, promise: Promise[T]): Unit = {
      val f = Future(body)
      f onComplete {
        case Success(t) => promise.success(t)
        case Failure(ex) =>
          if (attemptCount >= numTries) {
            promise.failure(ex)
          } else {
            loop(attemptCount + 1, promise)
          }
      }
    }

    val p = Promise[T]()

    loop(1, p)
    p.future
  }

  def retryFuture2[T](numTries: Int)(body: => T): Future[T] = {
    Future {
      body
    } recoverWith {
      case ex if numTries > 1 => retryFuture2(numTries - 1)(body)
    }
  }

  def retryWithFutureEither[R](numTries: Int)(body: => Future[Either[Throwable, R]]): Future[Either[Throwable, R]] = {
    def executeWithRecover: Future[Either[Throwable, R]] = body recover {
      case NonFatal(e) => Left(e)
    }
    def loop(currentCount: Int, currentResult: Either[Throwable, R]): Future[Either[Throwable, R]] = currentResult match {
      case r@Right(value) => Future.successful(r)
      case Left(err) if currentCount > 0 =>
        println(s"tring again $currentCount ")
        for {
          currentExecutionResult <- executeWithRecover
          finalResult <- loop(currentCount - 1, currentExecutionResult)
        } yield finalResult

      case l@Left(err) => Future.successful(l)
    }

    loop(numTries, Left(new Exception("Initial")))
  }

  @tailrec
  def retry[T](numTries: Int)(fn: => T): Try[T] = {
    val res = Try {
      fn
    }
    res match {
      case Success(x) => res
      case _ if numTries > 1 => retry(numTries - 1)(fn)
      case _ => res
    }
  }

}
