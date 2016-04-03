package com.learn.concurrency.futures

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.io.Source

/**
 * Created by gabbi on 24/01/15.
 */
object Nettiquette {
  import scala.concurrent.ExecutionContext.Implicits.global
  private val nettiquetteUrl = "http://www.ietf.org/rfc/rfc1855.tx"
  private val urlSpecUrl = "http://www.w3.org/Addressing/URL/url-spec.txt"

  def replyOnSpamEmail():Future[String] = {
    val nettiquette:Future[String] = fetchUrlText(nettiquetteUrl)
    val urlSpec:Future[String] = fetchUrlText(urlSpecUrl)

    val answer = for{
      nettiquetteText <- nettiquette
      urlText <- urlSpec
    }yield {
      s"First Read this $nettiquetteText , then try this $urlText"
    }

    answer recover {
      case e:java.io.FileNotFoundException => "ftp links can also point to files on our servers"
    }
  }

  private def fetchUrlText(url: String): Future[String] = {
    Future {
      Source.fromURL(url).mkString
    }
  }
}

object NettiquetteTest extends App{
  val answer = Nettiquette.replyOnSpamEmail()
  val res = Await.result(answer,Duration.Inf)
  println(res)
}
