package blogs.beautifulFolds

import cats.kernel.Monoid

/**
  * Created by gabbi on 20/12/2016.
  */
case class Average[A: Numeric](numerator: A, denominator: Int)

object Average {
  implicit def averageMonoid[A](implicit na: Numeric[A]) = new Monoid[Average[A]] {

    override def empty: Average[A] = Average(implicitly[Numeric[A]].zero, 0)

    override def combine(x: Average[A], y: Average[A]): Average[A] = Average(
      implicitly[Numeric[A]].plus(x.numerator, y.numerator),
      x.denominator + y.denominator
    )
  }
}