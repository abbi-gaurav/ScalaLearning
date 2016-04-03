package com.learn.rxScala.demo

import rx.lang.scala.Observable
import scala.concurrent.duration._

/**
 * Created by gabbi on 29/12/14.
 */
object Basic extends App{
  private def factors(n:Long) = Seq.range(1l, n+1).filter(n % _ == 0)

  private val clock:Observable[Long] = Observable.timer(0 seconds, 1 second)

  val primes = clock.filter(n => factors(n) == Seq(1,n))

  primes.foreach(println)

  System.in.read
}
