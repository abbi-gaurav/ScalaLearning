package com.learn.concurrency.futures

import java.io.File

import scala.concurrent.Future
import scala.io.Source
import org.apache.commons.io.FileUtils._
import scala.collection.convert.decorateAsScala._

/**
 * Created by gabbi on 19/01/15.
 */
object FuturesComposition extends App{
  import scala.concurrent.ExecutionContext.Implicits.global
  def blackListFile(name:String):Future[List[String]] = Future{
    val lines = Source.fromFile(name).getLines()
    lines filter {x => !(x.isEmpty || x.startsWith("#"))} toList
  }

  def findFiles(patterns:List[String]) = {
    val root = new File(".")
    for{
      file <- iterateFiles(root, null, true).asScala.toList
      pat <- patterns
      filePath = file.getCanonicalPath
      absPat = root.getCanonicalPath+File.separator+pat
      if filePath.contains(absPat)
    }yield filePath
  }

  def blackListed(name:String) = blackListFile(name) map findFiles

  def longestLine(name:String) = {
    val lines = Future{Source.fromFile(name).getLines()}
    for{line <- lines} yield line.maxBy(_.length)
  }
}
