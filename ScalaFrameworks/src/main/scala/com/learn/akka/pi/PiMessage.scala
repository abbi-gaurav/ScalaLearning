package com.learn.akka.pi

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorRef, Props}
import akka.routing.RoundRobinPool

import scala.concurrent.duration.Duration

/**
 * Created by gabbi on 17/08/14.
 */
sealed trait PiMessage

case object Calculate extends PiMessage

case class Work(start: Int, nrOfElements: Int) extends PiMessage

case class Result(value: Double) extends PiMessage

case class PiApproximation(pi: Double, duration: Duration)

class Worker extends Actor {

  def calculate(start: Int, nrOfElements: Int): Double =
    (start until (start + nrOfElements)).foldLeft(0.0) { case (acc, x) => acc + 4.0 * (1 - (x % 2) * 2) / (2 * x + 1)}

  override def receive: Receive = {
    case Work(start, nrOfElements) => sender ! Result(calculate(start, nrOfElements))
  }
}

class Master(nrOfWorkers: Int, nrOfMessages: Int, nrOfElements: Int, listener: ActorRef) extends Actor {
  var pi: Double = _
  var nrOfResults: Int = _
  val start: Long = System.currentTimeMillis
  val workerRouter = context.actorOf(Props[Worker].withRouter(RoundRobinPool(nrOfWorkers)), name = "workerRouter")

  override def receive: Actor.Receive = {
    case Calculate => for (i <- 0 until nrOfMessages) workerRouter ! Work(i * nrOfElements, nrOfElements)
    case Result(value) =>
      pi += value
      nrOfResults += 1
      if (nrOfResults == nrOfMessages) {
        listener ! PiApproximation(pi, Duration(System.currentTimeMillis - start, TimeUnit.MILLISECONDS))
        context.stop(self)
      }
  }
}

class Listener extends Actor{
  override def receive: Actor.Receive = {
    case PiApproximation(pi, duration) =>
      println("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s"
        .format(pi, duration))
      context.system.shutdown()
  }
}
