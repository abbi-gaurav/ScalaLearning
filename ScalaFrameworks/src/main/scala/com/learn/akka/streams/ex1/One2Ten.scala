package com.learn.akka.streams.ex1

import akka.stream.{ActorFlowMaterializer}
import akka.stream.scaladsl.{Keep, Sink, Source}
import akka.actor.ActorSystem
import com.learn.concurrency.futures._
/**
 * Created by gabbi on 06/03/15.
 */
object One2Ten extends App{
  implicit val system = ActorSystem("One2Ten")
  implicit val flowMaterializer = ActorFlowMaterializer()
  val source = Source(1 to 10)
  val sink = Sink.fold[Int,Int](0)(_ + _)
  val runnable = source.toMat(sink)(Keep.right)
  val sum = runnable.run()

  logFuture(sum)
  System.in.read()
}
