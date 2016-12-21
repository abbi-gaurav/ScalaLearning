package blogs.beautifulFolds

/**
  * Created by gabbi on 20/12/2016.
  */

import cats.Monoid
import cats.implicits._

import scala.Ordering.Implicits._

object CustomMonoids {
  val unitMonoid: Monoid[Unit] = catsKernelStdAlgebraForUnit

  case class Max[A: Ordering](v: A)

  def maxMonoid[A: Ordering](minValue: A): Monoid[Max[A]] = new Monoid[Max[A]] {
    override def empty: Max[A] = Max(v = minValue)

    override def combine(x: Max[A], y: Max[A]): Max[A] = if (x.v < y.v) y else x
  }

  def numProductMonoid[A: Numeric]: Monoid[A] = new Monoid[A] {
    override def empty: A = implicitly[Numeric[A]].one

    override def combine(x: A, y: A): A = implicitly[Numeric[A]].times(x, y)
  }

  def firstMonoid[T]: Monoid[Option[T]] = new Monoid[Option[T]] {
    override def empty: Option[T] = None

    override def combine(x: Option[T], y: Option[T]): Option[T] = x.orElse(y)
  }

  def lastMonoid[T]: Monoid[Option[T]] = new Monoid[Option[T]] {
    override def empty: Option[T] = None

    override def combine(x: Option[T], y: Option[T]): Option[T] = y.orElse(x)
  }

  def andMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    override def empty: Boolean = true

    override def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  def orMonoid = new Monoid[Boolean] {
    override def empty: Boolean = false

    override def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  def intMonoid: Monoid[Int] = catsKernelStdGroupForInt

}
