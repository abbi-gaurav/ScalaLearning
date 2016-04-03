package com.learn.concurrency.promises

import java.io.File

import org.apache.commons.io.monitor._

import scala.concurrent.Promise

/**
 * Created by gabbi on 25/01/15.
 */
object DirectoryWatcher extends App {
  def fileCreated(dir: String) = {
    val p = Promise[String]()
    val fileMonitor = new FileAlterationMonitor(1000)
    val observer = new FileAlterationObserver(dir)
    val listener = new FileAlterationListenerAdaptor {
      override def onFileCreate(file: File): Unit = {
        try p.success(file.getName) finally fileMonitor.stop()
      }
    }
    observer.addListener(listener)
    fileMonitor.addObserver(observer)
    fileMonitor.start()
    p.future
  }

  val future = fileCreated("/Users/gabbi/test")

  import com.learn.concurrency.futures._

  logFuture(future)

  System.in.read()
}
