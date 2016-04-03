package com.explore.scallop

import org.rogach.scallop.ScallopConf

/**
 * Created by gabbi on 01/01/15.
 */
class Basic(arguments:Seq[String]) extends ScallopConf(arguments){

  val apples = opt[Int](required = true)
  val bananas = opt[Int]()
}

object BasicUsage extends App{
  private val conf: Basic = new Basic(args)
  private val apples: Int = conf.apples()
  val bananas = conf.bananas.get
  println(s"$apples $bananas")
  println(conf.printHelp())
}
