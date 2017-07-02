package freeMonads

import cats.data.Xor
import cats.free.Free
import cats.{Id, ~>}
import freeMonads.Orders._
import cats.free.Free._
/**
  * Created by gabbi on 04/09/16.
  */

sealed trait Orders[T]

object Orders {
  type Symbol = String
  type Response = String
  type ErrorOr[A] = Xor[String, A]

  def buy(stock: Symbol, amount: Int): Free[Orders, Response] = liftF[Orders, Response](Buy(stock, amount))

  def sell(stock: Symbol, amount: Int): Free[Orders, Response] = liftF[Orders, Response](Sell(stock, amount))

  def orderPrinter: Orders ~> Id = new (Orders ~> Id) {
    def apply[A](fa: Orders[A]): Id[A] = fa match {
      case Buy(s, a) =>
        println(s"Buying $a $s ")
        "ok"
      case Sell(stock, amount) =>
        println(s"selling $amount $stock")
        "ok"
    }
  }

  import cats.syntax.xor._
  def xorInterpreter: Orders ~> ErrorOr = new (Orders ~> ErrorOr) {
    override def apply[A](fa: Orders[A]): ErrorOr[A] = fa match {
      case Buy(stock,amount) =>
        s"$stock - $amount".right
      case Sell(_,_) =>
        s"Why sell?".left
    }
  }
}

case class Buy(stock: Symbol, amount: Int) extends Orders[Response]

case class Sell(stock: Symbol, amount: Int) extends Orders[Response]