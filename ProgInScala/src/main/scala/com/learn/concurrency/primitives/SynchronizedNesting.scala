package com.learn.concurrency.primitives

/**
 * Created by gabbi on 30/12/14.
 */
object SynchronizedNesting extends App{
  import scala.collection._
  private val transfers = mutable.ArrayBuffer[String]()
  def logTransfers(name:String, amount:Int) = transfers.synchronized{
    transfers += s"transfer to account $name the amount $amount"
  }

  class Account(val name:String, var amount:Int){
    val uid = ThreadsProtectedUid.getUniqueId
  }

  def add(account: Account, amount:Int) = account.synchronized{
    account.amount += amount
    if(amount > 10) logTransfers(account.name, amount)
  }

  val jane = new Account("Jane",100)
  val john = new Account("John", 100)

  import com.learn.concurrency.utils._
  val t1 = thread{
    add(jane,5)
  }

  val t2 = thread{ add(john, 70)}

  val t3 = thread(add(jane, 50))

  t1.join();t2.join(); t3.join()

  import com.learn.concurrency.utils.Logger._
  log(s"----transfers-------$transfers")
}
