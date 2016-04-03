package com.learn.concurrency.tools

/**
 * Created by gabbi on 17/01/15.
 */
object CollectionsTrieMapBulk extends App{
  val names = new scala.collection.concurrent.TrieMap[String,Int]()
  names("Johnny") = 0; names("Jane") = 0; names("Jack") = 0

  import com.learn.concurrency.utils._
  execute{for(n <- 10 until 100) names(s"John $n") = n}
  execute{
    Logger.log("snapshot time")
    for(n <-names.map(_._1).toSeq.sorted) Logger.log(s"name : $n")
  }
  System.in.read()
}
