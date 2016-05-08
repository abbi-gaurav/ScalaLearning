package com.learn.fpConcepts.hof.fds

/**
  * Created by gabbi on 30/04/16.
  */
sealed trait Tree[+A] {
  def size: Int = this match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + l.size + r.size
  }

  def depth: Int = this match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + (l.depth max r.depth)
  }

  def map[B](f: A => B): Tree[B] = this match {
    case Leaf(x) => Leaf(f(x))
    case Branch(l, r) => Branch(l.map(f), r.map(f))
  }

  def fold[B](f: A => B)(g: (B, B) => B): B = this match {
    case Leaf(x) => f(x)
    case Branch(l, r) => g(l.fold(f)(g), r.fold(f)(g))
  }

  def mapFold[B](f: A => B): Tree[B] = this.fold(a => Leaf(f(a)): Tree[B])(Branch(_, _))

  def depthFold: Int = this.fold(_ => 0)(1 + _ + _)

  def sizeFold: Int = this.fold(_ => 1)(1 + _ + _)
}

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

object Tree {
  def maximum(tree: Tree[Int]): Int = {
    def loop(current: Tree[Int], acc: Int): Int = current match {
      case Leaf(x) => acc max x
      case Branch(l, r) => loop(l, acc) max loop(r, acc)
    }
    loop(tree, Int.MinValue)
  }

  def maximumFold(tree: Tree[Int]): Int = tree.fold(identity)(_ max _)

}