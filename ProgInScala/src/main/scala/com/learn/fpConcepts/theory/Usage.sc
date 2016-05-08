import com.learn.fpConcepts.theory.PartialNCurrying._

val b2c: (String) => List[String] = partial1(3, (a: Int, b: String) => List.fill(a)(b))
b2c("ss")

val curried: (String) => (Int) => List[String] = curry((a: String, b: Int) => List.fill(b)(a))
curried("gg")(3)

val uncurried: (Int, String) => List[String] = uncurry((n:Int) => (str:String) => List.fill(n)(str))
uncurried(2,"ss")