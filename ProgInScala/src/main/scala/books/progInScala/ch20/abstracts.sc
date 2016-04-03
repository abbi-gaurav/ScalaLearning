import books.progInScala.ch20.Rational
//preinitialized fields
new {
  val numer = 2
  val denom = 3
} with Rational

object twoThirds extends {
  val numer = 2
  val denom = 3
} with Rational