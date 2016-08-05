trait Plus[A] {
  def plus(a1: A, a2: A): A
}

def plus[A: Plus](a1: A, a2: A): A = implicitly[Plus[A]].plus(a1, a2)


trait Monoid[A] {
  def mappend(a1: A, a2: A): A

  def mZero: A
}

object Monoid {
  implicit val IntMonoid = new Monoid[Int] {
    override def mappend(a1: Int, a2: Int): Int = a1 + a2

    override def mZero: Int = 0
  }
}

trait FoldLeft[F[_]] {
  def foldLeft[A, B](xs: F[A], b: B, f: (B, A) => B): B
}

object FoldLeft {
  implicit val FoldLeftList: FoldLeft[List] = new FoldLeft[List] {
    override def foldLeft[A, B](xs: List[A], b: B, f: (B, A) => B): B = xs.foldLeft(b)(f)
  }
}

def sum[M[_] : FoldLeft, A: Monoid](xs: M[A]): A = {
  val m = implicitly[Monoid[A]]
  val fl = implicitly[FoldLeft[M]]
  fl.foldLeft(xs, m.mZero, m.mappend)
}

trait MonoidOp[A] {
  val F: Monoid[A]
  val value: A

  def |+|(a2: A) = F.mappend(value, a2)
}

implicit def toMonoidOp[A: Monoid](a: A): MonoidOp[A] = new MonoidOp[A] {
  override val F: Monoid[A] = implicitly[Monoid[A]]
  override val value: A = a
}

3 |+| 4