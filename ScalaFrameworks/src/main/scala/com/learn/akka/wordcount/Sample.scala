package com.learn.akka.wordcount

import akka.actor.{ActorSystem, Props}
import akka.dispatch.ExecutionContexts._
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Future
import scala.concurrent.duration._

/**
 * Created by gabbi on 27/12/14.
 */
object Sample extends App {
  calculate

  private def calculate:Unit = {
    val system = ActorSystem("wordCount")
    val actor = system.actorOf(Props(new WordsInFileCounter(args(0))))

    implicit val timeout = Timeout(25 seconds)
    println("sending message")
    val future = actor ? ProcessFileMsg

    implicit val ec = global

    future map { result =>
      println(s"total number of words $result")
      system.shutdown()
    }
  }
}
