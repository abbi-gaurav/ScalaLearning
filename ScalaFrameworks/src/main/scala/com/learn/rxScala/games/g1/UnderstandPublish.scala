package com.learn.rxScala.games.g1

import rx.lang.scala.Observable
import scala.concurrent.duration._

/**
 * Created by gabbi on 29/12/14.
 */
object UnderstandPublish extends App{
  val clock = Observable.timer(0 seconds, 1 second).publish
  clock.connect

  clock.subscribe(x => println(s">> $x"))
  Thread.sleep(2000)
  clock.subscribe(x => println(s"++ $x"))

  System.in.read()
}
