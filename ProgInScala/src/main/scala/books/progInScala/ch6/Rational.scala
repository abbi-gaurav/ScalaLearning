package books.progInScala.ch6

/**
 * Created by gabbi on 24/06/14.
 */
class Rational(n: Int, d: Int) extends Ordered[Rational] {

  def this(n: Int) = this(n, 1)

  private lazy val g = {
    require(d != 0)
    gcd(n.abs, d.abs)
  }
  lazy val numer = n / g
  lazy val denom = d / g

  override def toString = numer + "/" + denom

  def unary_- : Rational = new Rational(-numer, denom)

  def +(that: Rational) = new Rational((numer * that.denom) + (that.numer * denom), denom * that.denom)

  def +(x: Int): Rational = this + new Rational(x)

  def < (x: Int): Boolean = this < new Rational(x)

  def max(that: Rational) = if (this < that) that else this

  def max(x: Int): Rational = this max new Rational(x)

  def *(that: Rational) = new Rational(numer * that.numer, denom * that.denom)

  def *(x: Int): Rational = this * new Rational(x)

  def -(that: Rational) = this + (-that)

  def -(x: Int): Rational = this - new Rational(x)

  def /(that: Rational) = this * new Rational(that.denom, that.numer)

  def /(x: Int): Rational = this / new Rational(x)

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  override def compare(that: Rational): Int = this.numer * that.denom - that.numer * this.denom
}