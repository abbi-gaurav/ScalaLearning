import com.learn.fpConcepts.composition.SummationFunction._
summateTailRec(1,10)(identity)
summateTailRec(1,10)(x => x*x)
summateTailRec(1,10)(x => x*x*x)

summateFoldLeft(1,10)(identity)
summateFoldLeft(1,10)(x => x*x)
summateFoldLeft(1,10)(x => x*x*x)

summateFoldRight(1,10)(identity)
summateFoldRight(1,10)(x => x*x)
summateFoldRight(1,10)(x => x*x*x)