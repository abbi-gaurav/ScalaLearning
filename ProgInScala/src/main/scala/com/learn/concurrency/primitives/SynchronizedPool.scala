package com.learn.concurrency.primitives

import scala.annotation.tailrec
import scala.collection.mutable

/**
 * Created by gabbi on 31/12/14.
 */
object SynchronizedPool extends App {
  private val tasks = mutable.Queue[() => Unit]()

  /**
   * with graceful shutdown idiom
   */
  private object Worker extends Thread {
    private var terminated = false

    @tailrec
    override def run(): Unit = poll() match{
      case Some(task) => task(); run()
      case None =>
    }

    private def poll(): Option[() => Unit] = tasks.synchronized {
      while (tasks.isEmpty && !terminated) tasks.wait()
      if(!terminated) Some(tasks.dequeue()) else None
    }

    def shutdown() = tasks.synchronized{
      terminated = true
      tasks.notify()
    }
  }

  /*private object Worker extends Thread {
    setDaemon(true)

    override def run(): Unit = while (true) {
      val task = poll()
      task()
    }

    def poll(): () => Unit = tasks.synchronized {
      while (tasks.isEmpty) tasks.wait()
      tasks.dequeue()
    }
  }*/

  Worker.start()

  def asynchronous(body: => Unit) = tasks.synchronized{
    tasks.enqueue(() => body)
    tasks.notify()
  }

  import com.learn.concurrency.utils.Logger._
  asynchronous(log(s" hello ${Thread.currentThread()}"))
  asynchronous(log(s"hi ${Thread.currentThread()}"))
  Thread.sleep(5000)
  Worker.shutdown()
}
