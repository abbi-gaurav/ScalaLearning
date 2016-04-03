package com.learn.concurrency.primitives

/**
 * Created by gabbi on 30/12/14.
 */
object ThreadsProtectedUid{
  private var uidCount = 0L

  def getUniqueId = this.synchronized{
    val freshUid = uidCount+1
    uidCount = freshUid
    freshUid
  }

}
