package com.learn.fpConcepts.purelFuncState

/**
  * Created by gabbi on 25/07/16.
  */
trait RNG {
  type Rand[+A] = RNG => (A, RNG)

  def nextInt: (Int, RNG)

  def unit[A](a: A): Rand[A] = rng => (a, rng)

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = (rng: RNG) => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }

  def positiveInt(rng: RNG): (Int, RNG) = {
    val (ri, rng2) = rng.nextInt
    (if (ri < 0) -(ri + 1) else ri, rng2)
  }

  def positiveMax(n: Int): Rand[Int] = map(positiveInt)(x => x / n)

  def double: Rand[Double] = map(positiveInt)(_ / (Int.MaxValue.toDouble + 1))

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = rng => {
    val (a, rngA) = ra(rng)
    val (b, rngB) = rb(rng)
    (f(a, b), rngB)
  }

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = fs.foldRight(unit(List[A]())) {
    case (f: Rand[A], acc: Rand[List[A]]) => map2(f, acc)(_ :: _)
  }

  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = (rng: RNG) => {
    val (a, rA) = f(rng)
    g(a)(rng)
  }

  def mapInFM[A, B](s: Rand[A])(f: A => B): Rand[B] = flatMap(s)(a => unit(f(a)))

  def map2InFM[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = {
    flatMap(ra)(a => flatMap(rb)(b => unit(f(a, b))))
  }

}

object RNG {
  def double(rNG: RNG): (Double, RNG) = {
    val (ri, rng2) = rNG.nextInt
    (ri.toDouble / Int.MaxValue, rng2)
  }

  def ints(count: Int)(rNG: RNG): (List[Int], RNG) = {
    (0 until count).foldRight((List[Int](), rNG)) { case (_, (list, cRNG)) =>
      val (x, nRNG) = cRNG.nextInt
      (x :: list, nRNG)
    }
  }
}
