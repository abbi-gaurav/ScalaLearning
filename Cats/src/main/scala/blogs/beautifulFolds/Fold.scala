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
}
