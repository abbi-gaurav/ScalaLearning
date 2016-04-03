package com.learn.concurrency.futures

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source

/**
 * Created by gabbi on 18/01/15.
 */
object FuturesCallbacks extends App {
  def getSpec(resourceUrl: String): Future[List[(String, Int)]] = Future {
    val file = Source.fromURL(resourceUrl)
    try file.getLines().toList.zipWithIndex finally file.close()
  }

  def find(lines: List[(String, Int)], keyword: String) = {
    lines filter {
      case (line, idx) => line contains keyword
    } mkString "\n"
  }

  val urls = List("http://www.w3.org/Addressing/URL/url-spec.txt", "http://www.w3.org/non-existent-url-spec.txt")
  urls foreach { url =>
    work(url, getSpec)(op => find(op, "telnet"))
  }

  System.in.read()
}
