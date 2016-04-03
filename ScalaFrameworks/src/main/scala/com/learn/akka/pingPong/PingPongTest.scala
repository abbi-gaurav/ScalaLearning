package com.learn.akka.pingPong

import akka.actor._

/**
 * Created by gabbi on 28/12/14.
 */
case object PingMessage

case object PongMessage

case object StopMessage

case object StartMessage

case object Finish

class Pong extends Actor {
  override def receive: Actor.Receive = {
    case PingMessage =>
      print("pong")
      sender ! PongMessage
  }
}

class Ping(private val endListener: ActorRef) extends Actor {
  private var count = 0

  private def incrementAndPrint = {
    count += 1; println(" ping ")
  }

  private val pong: ActorRef = context.actorOf(Props[Pong], "pong")

  override def receive: Receive = {
    case StartMessage =>
      incrementAndPrint
      sendPing
    case PongMessage =>
      incrementAndPrint
      if(count > 99){
        endListener ! Finish
      }else{
        sendPing
      }
  }

  private def sendPing {
    pong ! PingMessage
  }
}

class EndListener extends Actor{
  override def receive: Actor.Receive = {
    case Finish => context.system.shutdown()
  }
}

object PingPongTest extends App {
  val system = ActorSystem("PingPongSystem")
  val actor = system.actorOf(Props(new Ping(system.actorOf(Props[EndListener], "end"))), "ping")
  actor ! StartMessage
}
