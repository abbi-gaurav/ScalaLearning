package com.learn.concurrency.basics

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.Random

/**
  * Created by gabbi on 02/10/14.
  */
object TestCoffeeMaking extends App {
  type CoffeeBeans = String
  type GroundCoffee = String
  type Milk = String

  case class Water(temperature: Int)

  case class GrindingException(msg: String) extends Exception(msg)

  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  private def sleep(): Unit = Thread.sleep(Random.nextInt(2000))

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future {
    if (beans == "baked beans") throw GrindingException("are you kidding???")
    println("start grinding")
    sleep()
    println("finished grinding")
    s"ground coffee of $beans"
  }

  def heatWater(water: Water): Future[Water] = Future {
    println("heating water")
    sleep()
    println("it is heated")
    water.copy(temperature = 100)
  }

  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
    println("milk frothing system engaged")
    sleep()
    println("done with frothing")
    s"frothed $milk"
  }

  def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
    println("brewing")
    sleep()
    println("it is brewed")
    "espresso"
  }

  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"

  def prepareCappuccino(): Future[Cappuccino] = {
    val groundCoffee = grind("arabia beans")
    val heatedWater = heatWater(Water(20))
    val frothedMilk = frothMilk("milk")

    for {
      g <- groundCoffee
      w <- heatedWater
      foam <- frothedMilk
      espresso <- brew(g, w)
    } yield combine(espresso, foam)
  }

  Await.result(prepareCappuccino(), Duration.Inf)
}
