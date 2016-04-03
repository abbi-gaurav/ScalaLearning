package books.progInScala.ch21

/**
 * Created by gabbi on 15/08/14.
 */
object ComplexImplicits {
  implicit def DoubleToComplex(x: Double): Complex = new Complex(x, 0.0)

  implicit def TupleToComplex(tuple: (Double, Double)): Complex = tuple match {
    case (x, y) => new Complex(x, y)
  }
}


class Complex(val real: Double, val imaginary: Double) {
  def +(that: Complex) = new Complex(this.real + that.real, this.imaginary + that.imaginary)

  override def toString = real + " + " + imaginary + "i"
}

import books.progInScala.ch21.ComplexImplicits._

object TestImplicits extends App {
  val c1: Complex = (2.0, 3.0)
  val c2:Complex = c1 + 5.0
  val c3 = 3.0 + c2

  println(c1, c2, c3)
}
