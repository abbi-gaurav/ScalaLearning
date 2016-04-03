package com.learn.concurrency.tools

import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConversions._

/**
 * Created by gabbi on 17/01/15.
 */
object CollectionsConcurrentMapBulk extends App{
  val names = new ConcurrentHashMap[String,Int]()
  names("Johnny") = 0; names("Jane") = 0; names("Jack") = 0

  import com.learn.concurrency.utils._
  execute{for(n <- 0 until 10) names(s"Johnny $n") = n}
  execute{for(n <-names) Logger.log(s"name : $n")}
  System.in.read()
}
