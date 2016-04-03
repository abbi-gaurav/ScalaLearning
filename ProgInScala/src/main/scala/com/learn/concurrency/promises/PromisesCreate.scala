package com.learn.concurrency.promises

import scala.concurrent.Promise

/**
 * Created by gabbi on 25/01/15.
 */
object PromisesCreate extends App{
  val p = Promise[String]()
  val q = Promise[String]()

  import com.learn.concurrency.futures._
  workWithFuture(p.future)(identity)
  workWithFuture(q.future)(identity)

  Thread.sleep(1000)
  p success "assigned"

  q failure new Exception("q failed")
  Thread.sleep(1000)
}
