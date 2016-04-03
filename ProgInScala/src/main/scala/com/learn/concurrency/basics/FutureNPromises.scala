package com.learn.concurrency.basics

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.io.{BufferedSource, Source}

/**
 * Created by gabbi on 29/09/14.
 */
object FutureNPromises extends App {

  def findKeyWord(keyword: String): Future[Option[Int]] = {
    val firstOccurrence: Future[Int] = Future {
      val source: BufferedSource = Source.fromFile("/Users/gabbi/learning/coursera/scala/intelliJIdeaWS/ProgInScala/src/main/resources/ipad.txt")
      source.toSeq.indexOfSlice(keyword)
    }
    for {
      x <- firstOccurrence
      y = if (x == -1) None else Some(x)
    } yield y
  }

  val f = findKeyWord("evernote")
  println(Await.result(f, Duration.Inf))
}
