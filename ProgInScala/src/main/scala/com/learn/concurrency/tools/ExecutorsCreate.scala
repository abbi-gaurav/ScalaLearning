package com.learn.concurrency.tools

import java.util.concurrent.{TimeUnit, ExecutorService}

import com.learn.concurrency.utils.Logger

import scala.concurrent.forkjoin.ForkJoinPool

/**
 * Created by gabbi on 01/01/15.
 */
object ExecutorsCreate extends App{
  private val executor:ExecutorService = new ForkJoinPool()
  executor.execute(new Runnable {
    override def run(): Unit = Logger.log("running from executor asynchronously")
  })
  executor.shutdown()
  executor.awaitTermination(5,TimeUnit.SECONDS)
}
