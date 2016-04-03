package com.learn.concurrency.promises

import scala.concurrent.{Promise, Future}
import scala.util.control.NonFatal

/**
 * Created by gabbi on 25/01/15.
 */
object PromisesCustomAsync extends App{
  import scala.concurrent.ExecutionContext.Implicits.global
  def myFuture[T](b: =>T):Future[T] = {
    val p = Promise[T]()
    global.execute(new Runnable{
      override def run(): Unit ={
        try{
          p.success(b)
        }catch {
          case NonFatal(e) => p.failure(e)
        }
      }
    })
    p.future
  }

  val f = myFuture{"naa" + "na" * 8 + " Katamari Damacy "}
  f foreach println

  System.in.read()
}
