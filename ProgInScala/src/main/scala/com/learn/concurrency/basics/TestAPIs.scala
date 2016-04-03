package com.learn.concurrency.basics

import com.learn.concurrency.basics.RetryTemplate._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

/**
  * Created by gabbi on 23/10/14.
  */
object TestAPIs extends App {


  def readFile: String = {
    scala.io.Source.fromFile("/invalid").mkString
  }

  testFutureRetry()
  testNormalRetry

  System.in.read()

  def testNormalRetry = retry(3) {
    readFile
  } match {
    case Success(str) => println(str)
    case Failure(e) => e.printStackTrace()
  }


  def testFutureRetry(): Unit = {
    val s = retryFuture(3) {
      readFile
    }
    s onComplete {
      case Success(str) => println(str)
      case Failure(e) => e.printStackTrace()
    }
  }
}
