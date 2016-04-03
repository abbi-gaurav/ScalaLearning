package books.progInScala.ch31

import scala.util.parsing.combinator._

/**
 * Created by gabbi on 09/11/14.
 */

object Arith extends JavaTokenParsers {
  def expr: Parser[Any] = term ~ rep("+" ~ term | "-" ~ term)

  def term: Parser[Any] = factor ~ rep("*" ~ factor | "/" ~ factor)

  def factor: Parser[Any] = floatingPointNumber | "(" ~expr~ ")"
}

object Test extends App{
  import Arith._

  def invoke(expression:String) = parseAll(expr, expression)
}

object JavaIdentifier extends RegexParsers{
  def ident : Parser[String] = """[a-zA-Z_]\w*""".r
}

