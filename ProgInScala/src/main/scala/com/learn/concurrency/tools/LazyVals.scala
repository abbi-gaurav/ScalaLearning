package com.learn.concurrency.tools

/**
 * Created by gabbi on 04/01/15.
 */
object LazyVals extends App{
  lazy val obj = new AnyRef
  lazy val non = s"made by Thread ${Thread.currentThread}"

  import com.learn.concurrency.utils.Logger._
  import com.learn.concurrency.utils._
  execute{
    log(s"ec seems obj = $obj")
    log(s"ec sees non = $non")
  }
  log(s"ec seems obj = $obj")
  log(s"ec sees non = $non")
  Thread.sleep(500)
}
