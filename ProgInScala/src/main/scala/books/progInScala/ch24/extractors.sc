import scala.util.matching.Regex

val decimal = new Regex("(-)?(\\d+)(\\.\\d*)?")
val decimal2 = new Regex("""(-)?(\d+)(\.\d*)?""")

decimal.pattern.toString == decimal2.pattern.toString

val decimal3 = """(-)?(\d+)(\.\d*)?""".r
decimal3.pattern.toString == decimal.pattern.toString

val input = "this is -0.45 to 100.98 by 34"
val input2 = "-0.45 to 100.98 by 34"
for(s <- decimal findAllIn input) println(s)
decimal3 findFirstIn input
decimal2 findPrefixOf input
decimal findPrefixOf input2
val decimal(sign, integerPart, decimalPart) = "-1.23"
for(decimal2(sign,intPart, decPart) <- decimal2 findAllIn input2){
  println(s"sign $sign intPart $intPart decPart $decPart")
}