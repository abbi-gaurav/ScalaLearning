package com.learn.concurrency

import scala.concurrent.{ExecutionContext, Future, Promise}

/**
 * Created by gabbi on 30/12/14.
 */
package object utils {

  import java.util._

  private val timer = new Timer(true)

  def thread(body: => Unit): Thread = {
    val t = new Thread() {
      override def run() = body
    }
    t.start
    t
  }

  def execute(body: => Unit): Unit = ExecutionContext.global.execute(
    new Runnable {
      override def run(): Unit = body
    }
  )

  def timeout(t: Long)(implicit ec:ExecutionContext): Future[Unit] = {
    val p = Promise[Unit]()
    timer.schedule(new TimerTask {
      override def run(): Unit = {
        p.success()
      }
    }, t)
    p.future
  }

  def delayedFuture[T](delay: Long, b: => T)(implicit ec:ExecutionContext): Future[T] = {
    timeout(delay) map { _ => b}
  }

  override def finalize() = timer.cancel()

  implicit class FutureOps[T](val self:Future[T]) {
    def or(that: Future[T])(implicit ec: ExecutionContext): Future[T] = {
      val p = Promise[T]()
      self onComplete p.tryComplete
      that onComplete p.tryComplete
      p.future
    }

    def timeoutWith(waitTime:Long, b: =>T)(implicit ec: ExecutionContext):Future[T] = {
      self or delayedFuture(waitTime, b)
    }
  }
}
