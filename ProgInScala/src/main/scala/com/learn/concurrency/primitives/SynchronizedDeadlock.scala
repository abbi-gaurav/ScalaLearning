package com.learn.concurrency.primitives

/**
 * Created by gabbi on 30/12/14.
 */
object SynchronizedDeadlock extends App{
  import SynchronizedNesting.Account
  import com.learn.concurrency.utils._

  def send(a:Account, b:Account, n:Int) = a.synchronized{
    b.synchronized{
      a.amount -= n
      b.amount += n
    }
  }

  def sendNoDeadLock(a:Account, b:Account, n:Int) = {
    def adjust(): Unit ={
      a.amount -= n
      b.amount += n
    }
    if(a.uid > b.uid){
      a.synchronized(b.synchronized(adjust()))
    }else{
      b.synchronized(a.synchronized(adjust()))
    }
  }

  val a = new Account("jack", 1000)
  val b = new Account("jill", 2000)
  val t1 = thread{for(i <- 0 until 1000) sendNoDeadLock (a,b,1)}
  val t2 = thread{for(i <- 0 until 1000) sendNoDeadLock(b,a,1)}
  t1.join()
  t2.join()

  Logger.log(s"a's money ${a.amount}, b's money ${b.amount}")
}
