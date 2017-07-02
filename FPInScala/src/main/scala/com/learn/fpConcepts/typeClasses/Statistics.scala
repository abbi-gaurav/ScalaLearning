package com.learn.fpConcepts.typeClasses

/**
  * Created by gabbi on 03/07/16.
  */
object Statistics {

  import Math.NumberLike

  def mean[T](xs: Vector[T])(implicit ev: NumberLike[T]): T = {
    ev.divide(xs.reduce(ev.plus), xs.size)
  }

  def median[T: NumberLike](xs: Vector[T]): T = xs(xs.size / 2)

  def quartiles[T: NumberLike](xs: Vector[T]): (T, T, T) = (xs(xs.size / 4), median(xs), xs(xs.size * (3 / 4)))

  def iqr[T: NumberLike](xs: Vector[T]): T = quartiles(xs) match {
    case (lq, _, uq) => implicitly[NumberLike[T]].minus(uq, lq)
  }
}
