package com.learn.concurrency.primitives

/**
 * Created by gabbi on 30/12/14.
 */
import com.learn.concurrency.utils._
import com.learn.concurrency.utils.Logger._
object ThreadsSleep extends App{
  val t = thread{
    Thread.sleep(1000)
    log("new thread running")
    Thread.sleep(1000)
    log("still running")
    Thread.sleep(1000)
    log("completed")
  }
  t.join()
  log("new thread joined")
}
