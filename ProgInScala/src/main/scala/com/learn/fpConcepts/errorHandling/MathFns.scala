package com.learn.fpConcepts.errorHandling

/**
  * Created by gabbi on 15/05/16.
  */
object MathFns {
  def mean(seq: Seq[Double]): Option[Double] = seq match {
    case Seq() => None
    case _ => Some(seq.sum / seq.length)
  }

  def variance(xs: Seq[Double]): Option[Double] = for {
    m <- mean(xs)
    v <- mean(xs map (x => math.pow(x - m, 2)))
  } yield v

  def variance2(xs: Seq[Double]): Option[Double] = mean(xs).flatMap { m =>
    mean(xs map (x => math.pow(x - m, 2)))
  }
}
