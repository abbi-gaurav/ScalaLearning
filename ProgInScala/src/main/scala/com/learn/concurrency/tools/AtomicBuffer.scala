package com.learn.concurrency.tools

import java.util.concurrent.atomic.AtomicReference

import com.learn.concurrency.utils._

import scala.annotation.tailrec
import scala.collection.mutable

/**
 * Created by gabbi on 04/01/15.
 */
class AtomicBuffer[T] {
  private val bufferRef = new AtomicReference[List[T]](Nil)

  @tailrec final def +=(x: T): Unit = {
    val xs = bufferRef.get()
    val newXs = x :: xs
    if (!bufferRef.compareAndSet(xs, newXs)) this.+=(x)
  }

  def values = bufferRef.get()
}

object AtomicBuffer2 extends App{
  private val buffer = mutable.ArrayBuffer[Int]()

  def asyncAdd(values: Seq[Int]) = execute {
    buffer.synchronized {
      buffer ++= values
      Logger.log(s"buffer values $buffer")
    }
  }

  asyncAdd(1 until 10)
  asyncAdd(11 until 20)

  val ab1 = new AtomicBuffer[Int]()
  1 to 10 foreach{x =>
    execute(ab1 += x)
  }
  println(ab1.values)

  Thread.sleep(1000)


}
