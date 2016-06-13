import com.learn.fpConcepts.errorHandling.Partial._
import com.learn.fpConcepts.errorHandling.{Error, Partial, Success}

val ps: List[Partial[String, Int]] = List(Success(1), Error(List("a")), Success(2), Error(List("b")))

sequence(ps)
traverse(ps)(x => Success(x.toDouble))

val ss: List[Partial[String, Int]] = List(Success(1), Success(2))
sequence(ss)
traverse(ss)(x => Success(x.toDouble))

Success(1) map (_.toDouble)

(Error(List("s")): Partial[String, Int]) map (_.toDouble)

Error(List("s")).orElse(Success(5))

for {
  name <- Success("gg")
  age <- Error(List("invalid age"))
  dept <- Error(List("invalid dept"))
} yield (name, age, dept)