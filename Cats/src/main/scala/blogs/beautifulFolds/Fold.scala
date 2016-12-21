package blogs.beautifulFolds

import cats.Monoid

/**
  * Created by gabbi on 18/12/2016.
  */
trait Fold[I, O] {
  type M

  def m: Monoid[M]

  def tally: I => M

  def summarize: M => O
}

object Fold {
  def apply[I, O, _M](_m: Monoid[_M])(_tally: I => _M, _summarize: _M => O) = new Fold[I, O] {

    override type M = _M

    override def m: cats.Monoid[M] = _m

    override def tally: (I) => this.M = _tally

    override def summarize: (M) => O = _summarize
  }

  def fold[I, O](input: Seq[I])(f: Fold[I, O]): O = {
    val reduced: f.M = input.foldLeft(f.m.empty) {
      case (acc, x) => f.m.combine(acc, f.tally(x))
    }
    f.summarize(reduced)
  }

  def sum[M](implicit m: Monoid[M]): Fold[M, M] = Fold(m)(identity, identity)

  import Average._

  def average[A: Numeric]: Fold[A, Double] = Fold(averageMonoid)(
    _tally = Average(_, 1),
    _summarize = a => implicitly[Numeric[A]].toDouble(a.numerator) / a.denominator
  )

  import CustomMonoids._

  def first[T]: Fold[T, Option[T]] = Fold(firstMonoid[T])(Some(_), identity)

  def last[T]: Fold[T, Option[T]] = Fold(lastMonoid[T])(Some(_), identity)

  def all[A](p: A => Boolean): Fold[A, Boolean] = Fold(andMonoid)(p, identity)

  def any[A](p: A => Boolean): Fold[A, Boolean] = Fold(orMonoid)(p, identity)

  def product[A: Numeric]: Fold[A, A] = Fold(numProductMonoid[A])(identity, identity)

  def length[A]: Fold[A, Int] = Fold(intMonoid)(_ => 1, identity)
}
