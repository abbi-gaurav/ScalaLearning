package com.learn.concurrency.basics

import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Random, Success}
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by gabbi on 02/10/14.
 */
object Government extends App {
  private val rand = new Random(System.currentTimeMillis())

  case class LameExcuse(msg: String) extends Exception(msg)

  case class TaxCut(cut: Int)

  def redeemCampaignPledge(): Future[TaxCut] = {
    val promise = Promise[TaxCut]()
    Future {
      println("Starting the new legislative period")
      Thread.sleep(2000)
      promise.complete(if (rand.nextInt() % 2 == 0) Success(TaxCut(20)) else Failure(LameExcuse("global crisis")))
      println("we did at best what we can do")
    }
    promise.future
  }

  val f = redeemCampaignPledge()

  f onComplete{
    case Success(TaxCut(reduction)) => println(s"taxes reduced by $reduction points")
    case Failure(ex) => println(s"promises broken due to lame excuse ${ex.getLocalizedMessage}")
  }

  System.in.read()

}
