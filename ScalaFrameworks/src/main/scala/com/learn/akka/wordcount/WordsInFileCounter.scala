package com.learn.akka.wordcount

import akka.actor.{ActorRef, Actor, Props}

/**
 * Created by gabbi on 27/12/14.
 */
class WordsInFileCounter(fileName: String) extends Actor {
  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender:Option[ActorRef] = None

  private def spawnWordCount(line: String) = {
    totalLines += 1
    context.actorOf(Props[StringCounter]) ! ProcessStringMsg(line)
  }

  override def receive: Receive = {
    case ProcessFileMsg => {
      fileProcess()
    }
    case StringProcessedMsg(numWords) => processResponse(numWords)
  }

  private def fileProcess():Unit = {
    if (fileSender == None) {
      fileSender = Some(sender())

      toLines(fileName).foreach(line =>
        spawnWordCount(line)
      )
    } else {
      println("duplicate processing file msg ignored")
    }
  }

  private def toLines(fileName: String): Iterator[String] = {
    import scala.io._
    Source.fromFile(fileName).getLines()
  }

  private def processResponse(numwords:Int):Unit = {
    result += numwords
    linesProcessed += 1

    if(totalLines == linesProcessed){
      fileSender map (_ ! result)
    }
  }
}
