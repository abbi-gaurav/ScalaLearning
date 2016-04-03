package com.ds.trees

/**
 * Created by gabbi on 14/09/14.
 */
sealed abstract class Tree[+A <% Ordered[A]] {
  def value: A

  def isEmpty: Boolean

  def left: Tree[A]

  def right: Tree[A]

  def size: Int

  def add[B >: A <% Ordered[B]](x: B): Tree[B] = {
    if (isEmpty) Tree(x)
    else {
      if (x < value) Tree(value, left.add(x), right)
      else if (x > value) Tree(value, left, right.add(x))
      else this
    }
  }

  def contains[B >: A <% Ordered[B]](x: B): Boolean = {
    if (isEmpty) false
    else {
      if (x < value) left.contains(x)
      else if (x > value) right.contains(x)
      else true
    }
  }

  def height: Int = {
    if (isEmpty) 0
    else 1 + math.max(left.height, right.height)
  }

  def fold[B](n: B)(op: (B, A) => B) = {
    def loop(t: Tree[A], acc: B): B = {
      if (t.isEmpty) acc
      else loop(t.right, op(loop(t.left, acc), value))
    }
    loop(this, n)
  }

  def filter(p: A => Boolean): Tree[A] = filterAcc(p, Leaf)

  def filterUsingFold(p: A => Boolean): Tree[A] = {
    this.fold(Tree[A]()) { (acc, x) =>
      if (p(x)) acc.add(x)
      else acc
    }
  }

  def union[B >: A <% Ordered[B]](other: Tree[B]): Tree[B] = filterAcc(_ => true, other)

  private def filterAcc[B >: A <% Ordered[B]](p: A => Boolean, acc: Tree[B]): Tree[B] = {
    if (isEmpty) acc
    else {
      p(value) match {
        case true => right.filterAcc(p, left.filterAcc(p, acc.add(value)))
        case _ => right.filterAcc(p, left.filterAcc(p, acc))
      }
    }
  }

  private[trees] def fail(msg: String) = throw new NoSuchElementException(msg)
}

case object Leaf extends Tree[Nothing] {
  override def size: Int = 0

  override def isEmpty: Boolean = true

  override def value: Nothing = fail("empty tree")

  override def left: Tree[Nothing] = fail("empty tree")

  override def right: Tree[Nothing] = fail("empty tree")
}

case class Branch[A <% Ordered[A]](value: A, left: Tree[A], right: Tree[A], size: Int) extends Tree[A] {
  override def isEmpty: Boolean = false
}

object Tree {
  def apply[A <% Ordered[A]](): Tree[A] = Leaf

  def apply[A <% Ordered[A]](x: A, left: Tree[A] = Leaf, right: Tree[A] = Leaf): Tree[A] = {
    Branch(x, left, right, left.size + right.size + 1)
  }
}
