package com.learn.concurrency.fileMgr

import com.learn.concurrency.utils.Logger

/**
 * Created by gabbi on 17/01/15.
 */
object TestFS extends App{
  val fs = new FileSystem("/Users/gabbi/IdeaProjects/ScalaFrameworks/src/main/scala/com/learn/concurrency/tools")
  Logger.log(s"all files in root dir : ${fs.allFiles mkString "\n" }")
}
