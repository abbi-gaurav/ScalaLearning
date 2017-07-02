package com.learn.fpConcepts.typeClasses

/**
  * Created by gabbi on 03/07/16.
  */
object Math {

  trait NumberLike[T] {
    def plus(x: T, y: T): T

    def divide(x: T, y: Int): T

    def minus(x: T, y: T): T
  }

  object NumberLike {

    implicit object NumberLikeDouble extends NumberLike[Double] {
      override def plus(x: Double, y: Double): Double = x + y

      override def divide(x: Double, y: Int): Double = x / y

      override def minus(x: Double, y: Double): Double = x - y
    }

    implicit object NumberLikeInt extends NumberLike[Int] {
      override def plus(x: Int, y: Int): Int = x + y

      override def divide(x: Int, y: Int): Int = x / y

      override def minus(x: Int, y: Int): Int = x - y
    }

  }

}
