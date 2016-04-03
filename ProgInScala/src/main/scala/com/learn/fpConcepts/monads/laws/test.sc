import com.learn.fpConcepts.monads.laws.{Box, Container, Empty}

val m: Container[Double] = Box(9)

m flatMap (x => Box(x.toString))

m map (_.toString)

for {
  x <- m
} yield {
  if (x % 2 == 0) 'E' else 'O'
}
//functor identity law
m.map(x => x) == m

//functor compose law
def g(x: Double) = math.pow(x, 2)
def f(x: Double): Double = math.cbrt(x)
m map g map f
m map { x => f(g(x))}

//monad 0th law
m map g
m flatMap { x => f1(x)}

//monad 1st law
for {
  x <- m
  y <- Box(x)
} yield y

//Monad 2nd law
def f1(x: Double): Container[Double] = {
  Box(g(x))
}
def f2(x: Double): Container[Double] = Box(f(x))

Box(9) flatMap { x => f1(x)}
f1(9)

//Monad 3rd Law
m flatMap f1 flatMap f2
m flatMap { x => f1(x) flatMap f2}

val z: Container[Double] = Empty
//monadic zero 1st law
z flatMap f2
z map f

//m to zero in nothing flat
m flatMap { x => Empty}

//plus
z otherwise m
m otherwise z

//filter
m filter (10 >)