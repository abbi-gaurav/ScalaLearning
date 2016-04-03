package com.learn.concurrency.tools

import java.util.concurrent.{TimeUnit, ForkJoinPool}

import com.learn.concurrency.utils.Logger

import scala.concurrent.ExecutionContext

/**
 * Created by gabbi on 01/01/15.
 */
object ExecutionContextCreate extends App{
  val executionContext = ExecutionContext.fromExecutorService(new ForkJoinPool(2))
  executionContext.execute(new Runnable {
    override def run(): Unit = Logger.log("executing from exc ctx")
  })

  executionContext.shutdown()
  executionContext.awaitTermination(5,TimeUnit.SECONDS)
}
