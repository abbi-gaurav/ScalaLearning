package week1.rg

import scala.util.Random

/**
  * Created by gabbi on 29/05/16.
  */
trait RGenerator[+T] {
  self =>

  def generate: T

  def map[S](f: T => S): RGenerator[S] = new RGenerator[S] {
    override def generate: S = f(self.generate)
  }

  def flatMap[S](f: T => RGenerator[S]): RGenerator[S] = new RGenerator[S] {
    override def generate: S = f(self.generate).generate
  }
}

object RGenerator {
  val integers: RGenerator[Int] = new RGenerator[Int] {
    private val random = new Random()

    override def generate: Int = random.nextInt()
  }

  val booleans: RGenerator[Boolean] = for (x <- integers) yield x > 0

  def pairs[T, U](t: RGenerator[T], u: RGenerator[U]): RGenerator[(T, U)] = for {
    x <- t
    y <- u
  } yield (x, y)

  def single[T](x: T) = new RGenerator[T] {
    //an alias for this
    override def generate: T = x
  }

  def choose(lo: Int, hi: Int): RGenerator[Int] = {
    for (x <- integers) yield lo + x % (hi - lo)
  }

  def oneOf[T](xs: T*) = {
    for (idx <- choose(0, xs.length)) yield xs(idx)
  }

  def emptyList[T]: RGenerator[List[T]] = single(Nil)

  def nonEmptyList: RGenerator[List[Int]] = for {
    head <- integers
    tail <- lists
  } yield head :: tail

  def lists: RGenerator[List[Int]] = for {
    isEmpty <- booleans
    list <- if (isEmpty) emptyList else nonEmptyList
  } yield list


}
