package com.learn.concurrency.utils

/**
 * Created by gabbi on 30/12/14.
 */
object Logger {
  def log(msg:String) = println(msg)
  def warn(msg:String) = log(s"Warn::$msg")
  def debug(msg:String) = log(s"DEBUG::$msg")
  def error(msg:String, exception: Throwable) = log(s"ERROR::$msg\n${exception.getStackTrace mkString "\n"}")
}
