package com.learn.akka.wordcount

import akka.actor.Actor

/**
 * Created by gabbi on 27/12/14.
 */
class StringCounter extends Actor {

  override def receive: Receive = {
    case ProcessStringMsg(string) =>
      val numWords = string.split(" ").length
      sender ! StringProcessedMsg(numWords)

    case _ => println("unrecognized message")
  }
}
