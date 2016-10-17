package com.learn.fpConcepts.purelFuncState

/**
  * Created by gabbi on 17/10/2016.
  */

import com.learn.fpConcepts.purelFuncState.State._

sealed trait Input

case object Coin extends Input

case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int) {
}

object Machine {
  def operate(i: Input, m: Machine) = (i, m) match {
    case (Coin, Machine(_, 0, _)) => m
    case (Turn, Machine(true, _, _)) => m
    case (Coin, Machine(false, _, _)) => m
    case (Turn, Machine(false, candies, coins)) => Machine(locked = true, candies = candies - 1, coins = coins)
    case (Coin, Machine(true, candies, coins)) => Machine(locked = false, candies = candies, coins = coins + 1)
  }

  def simulateMachine(inputs: List[Input]): State[Machine, Int] = {
    val states: List[State[Machine, Unit]] = inputs.map(i => modify((s: Machine) => Machine.operate(i, s)))

    val state: State[Machine, Int] = for {
      x <- sequenceViaFoldRight(states)
      s <- get
    } yield s.coins

    state
  }
}

object Run extends App {
  val ips = List(Coin, Turn, Coin, Turn, Coin)

  val x: State[Machine, Int] = Machine.simulateMachine(ips)
  val y: (Int, Machine) = x.run(Machine(locked = true, candies = 10, coins = 10))
  println(y)
}
