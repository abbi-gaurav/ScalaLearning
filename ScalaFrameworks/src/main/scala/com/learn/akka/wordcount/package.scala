package com.learn.akka

/**
 * Created by gabbi on 27/12/14.
 */
package object wordcount {
  case object ProcessFileMsg
  case class ProcessStringMsg(string:String)
  case class StringProcessedMsg(words:Int)
}
