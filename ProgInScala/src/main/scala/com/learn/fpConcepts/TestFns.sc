import com.learn.fpConcepts.errorHandling.EitherOps._

val x: Either[String, (String, Int, Double)] = for {
  age <- Right(42)
  name <- Right[String, String]("invalid name")
  salary <- Right(1000000.0)
} yield (name, age, salary)

val eithersL = List[Either[String, Int]](Right(1), Left("2"), Right(3))
val eithersR = List[Either[String, Int]](Right(1), Right(2), Right(3))
sequence(eithersL)
sequence(eithersR)

traverse(eithersL)(x => Right(x.toDouble))
traverse(eithersR)(x => Right(x.toDouble))