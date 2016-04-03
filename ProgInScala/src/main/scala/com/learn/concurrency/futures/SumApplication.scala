package com.learn.concurrency.futures

import scala.concurrent.Future

/**
 * Created by gabbi on 20/08/14.
 */
object SumApplication extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  def delayIdentity(x: Int): Int = {
    Thread.sleep(3000)
    x
  }

  usingLists(1 to 9)

  //  normal()


  def usingLists(range: Range): Unit = {
    val startTime = System.currentTimeMillis
    val list = range.toList.map(x => Future(delayIdentity(x)))
    val future = Future.sequence(list)
    future onSuccess {
      case results =>
        val sum: Int = results.sum
        val elapsedTime = (System.currentTimeMillis - startTime) / 1000.0
        println("Sum of 1, 2 and 3 is " + sum + " calculated in " + elapsedTime + " seconds")
    }
  }

  def normal() {
    val startTime = System.currentTimeMillis

    val f1 = Future(delayIdentity(1))
    val f2 = Future(delayIdentity(2))
    val f3 = Future(delayIdentity(3))
    val future = for {
      x <- f1
      y <- f2
      z <- f3
    } yield x + y + z

    future onSuccess {
      case sum =>
        val elapsedTime = (System.currentTimeMillis - startTime) / 1000.0
        println("Sum of 1, 2 and 3 is " + sum + " calculated in " + elapsedTime + " seconds")
    }
  }


  System.in.read
}
