import books.progInScala.ch6.Rational

val x = new Rational(1, 2)
val y = new Rational(1,4)

x + y

x < y

x max y

x * y

x + x * y
x + (x*y)

x - y

x / y

-x

x + 1

x - 1

(x + 1) max 1

y / 2

implicit def intToRational(x:Int):Rational = new Rational(x)

2 * x

2 + y