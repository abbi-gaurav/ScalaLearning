package com.learn.fpConcepts.hof.folds

/**
  * Created by gabbi on 10/04/16.
  */
object FoldsImpl {
  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Nil => z
    case x :: xs => f(x, foldRight(xs, z)(f))
  }

  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case x :: xs => foldLeft(xs, f(z, x))(f)
  }

  def sumRight(l: List[Int]): Double = foldRight(l, 0.0)(_ + _)

  def productRight(l: List[Int]): Double = foldRight(l, 1.0)(_ * _)

  def sumLeft(l: List[Int]) = foldLeft(l, 0.0)(_ + _)

  def productLeft(l: List[Int]) = foldLeft(l, 0.0)(_ * _)

  def lengthLeft[A](l: List[A]) = foldLeft(l, 0) { case (acc, _) => acc + 1 }

  //reverse is required since fold right starts from the end
  def foldLeftUsingFoldRight[A, B](l: List[A], z: B)(f: (B, A) => B): B = {
    foldRight(l.reverse, z) { case (elem, acc) => f(acc, elem) }
  }

  //reverse is required since fold left starts from first element
  def foldRightUsingFoldLeft[A, B](l: List[A], z: B)(f: (A, B) => B): B = {
    foldLeft(l.reverse, z) { case (acc, elem) => f(elem, acc) }
  }

  def appendRight[A](a1: List[A], a2: List[A]): List[A] = {
    foldRightUsingFoldLeft(a1, a2) { case (x, acc) => x :: acc }
  }

  def reverseLeft[A](l: List[A]) = foldLeftUsingFoldRight(l, Nil: List[A]) { case (acc, elem) => elem :: acc }

  def concatenate[A](listOfLists: List[List[A]]): List[A] = {
    listOfLists.foldRight(Nil: List[A]) { case (x, acc) => appendRight(x, acc) }
  }
}
