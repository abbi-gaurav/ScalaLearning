package com.learn.concurrency.promises

import java.util.concurrent.CancellationException

import com.learn.concurrency.utils.Logger

import scala.concurrent.{Future,Promise}

/**
 * Created by gabbi on 05/02/15.
 */
object PromisesCancellation extends App{
  import concurrent.ExecutionContext.Implicits.global

  type Cancellable[T] = (Promise[Unit], Future[T])

  def cancellable[T](b: Future[Unit] => T):Cancellable[T] = {
    val cancel = Promise[Unit]
    val f = Future{
      val res = b(cancel.future)
      if(!cancel.tryFailure(new Exception)){
        throw new CancellationException()
      }
      res
    }
    (cancel,f)
  }

  val (cancelPromise,value) = cancellable{ cancelFuture =>
    var i = 0
    while(i<5){
      if(cancelFuture.isCompleted) throw new CancellationException
      Thread.sleep(500)
      Logger.log(s"$i : working")
      i += 1
    }
    "resulting value"
  }

  Thread.sleep(1500)
  cancelPromise trySuccess()
  Logger.log("computation cancelled")
  System.in.read()
}
