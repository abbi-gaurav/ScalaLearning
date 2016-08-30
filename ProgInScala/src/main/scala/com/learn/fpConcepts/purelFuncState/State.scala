package com.learn.fpConcepts.purelFuncState

/**
  * Created by gabbi on 30/08/16.
  * type State[S,+A] = S => (A,S)
  */

import com.learn.fpConcepts.purelFuncState.State._

case class State[S, +A](run: S => (A, S)) {
  def flatMap[B](g: A => State[S, B]): State[S, B] = State(s => {
    val (a, sa) = run(s)
    g(a).run(sa)
  })

  def map[B](f: A => B): State[S, B] = flatMap(a => unit(f(a)))

  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = {
    flatMap(a => sb.map(b => f(a, b)))
  }
}

object State {
  def unit[S, A](a: A): State[S, A] = State(s => (a, s))

  def sequenceViaFoldRight[S, A](sas: List[State[S, A]]): State[S, List[A]] = {
    val zero: State[S, List[A]] = unit[S, List[A]](List[A]())
    sas.foldRight(zero) {
      case (sa: State[S, A], acc: State[S, List[A]]) =>
        acc.map2(sa)((list, elem) => elem :: list)
    }
  }
}