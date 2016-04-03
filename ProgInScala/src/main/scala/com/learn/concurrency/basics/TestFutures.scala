package com.learn.concurrency.basics

import com.learn.concurrency.basics.RetryTemplate._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by gabbi on 06/03/16.
  */
object TestFutures extends App {
  def f(n: Int): Either[Throwable, Int] = {
    if (n < 3) throw new IllegalStateException(n.toString) else Right(n)
  }

  var n = 0
  val x: Future[Either[Throwable, Int]] = retryWithFutureEither(3) {
    n = n + 1
    Future(f(n))
  }
  val y: Either[Throwable, Int] = Await.result(x, Duration.Inf)
  println(y)
}
