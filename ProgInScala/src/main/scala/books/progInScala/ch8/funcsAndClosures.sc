val f = (_: Int) + (_: Int)

f(2, 4)

def sum(a: Int, b: Int) = a + b

val b = sum _
b.apply(1, 2)
b(1, 2)

def sum2(a: Int)(b: Int) = a + b
val b2 = sum2(2) _
b2.apply(1)
b2(1)

def sum3(a: Int, b: Int, c: Int) = a + b + c
val b3 = sum3(1, _: Int, 2)
b3(0)
b3(1)

def x(f: Int => Int) = f(2)
x(sum2(3))


//closure
var more = 1

val p = (x: Int) => x + more

p(2)
more = 3
p(2)

val p2 = (x: Int) => {
  more = -1;
  x + more;
}

p2(3)
more

def makeIncreaser(m: Int) = (x: Int) => x + m
val mi1 = makeIncreaser(1)
val mi2 = makeIncreaser(1000)

mi1(1)
mi2(1)

def echo(args: String*) = {
  args foreach (println)
  args.length
}
echo()
echo("one")
echo("2", "3")
val arr = Array("hi", "there")
echo(arr: _*)

def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)