package com.gabbi.profile

/**
  * Created by gabbi on 13/06/16.
  */
object TestHelper {
  def randomString(length: Int) = scala.util.Random.alphanumeric.take(length).mkString
}
