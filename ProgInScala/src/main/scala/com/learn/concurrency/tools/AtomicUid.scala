package com.learn.concurrency.tools

import java.util.concurrent.atomic.AtomicLong
import com.learn.concurrency.utils._
import scala.annotation.tailrec

/**
 * Created by gabbi on 01/01/15.
 */
import com.learn.concurrency.utils.Logger._

object AtomicUid extends App{
  private val uid = new AtomicLong(0L)
  def getUniqueId = uid.incrementAndGet()

  @tailrec def getUniqueId2:Long = {
    val oldId = uid.get()
    val newId = oldId+1
    if(uid.compareAndSet(oldId,newId)) newId
    else getUniqueId2
  }

  execute(log(s"uid async $getUniqueId"))
  execute(log(s"uid async $getUniqueId2"))

  log(s"uid in main $getUniqueId")
}
