package books.progInScala.ch31

import java.io.FileReader

import scala.util.parsing.combinator.JavaTokenParsers

/**
 * Created by gabbi on 09/11/14.
 */
object Json extends JavaTokenParsers {
  def value: Parser[Any] = (
        obj
      | arr
      | stringLiteral
      | floatingPointNumber ^^ (_.toDouble)
      | "null" ^^ (x=>null)
      | "true" ^^ (x=>true)
      | "false" ^^ (x=>false)
    )

  def obj: Parser[Map[String,Any]] = "{" ~> repsep(member, ",") <~ "}" ^^ (Map() ++ _)

  def arr: Parser[List[Any]] = "[" ~> repsep(value, ",") <~ "]"

  def member: Parser[(String,Any)] = stringLiteral ~ ":" ~ value ^^ {case name~":"~value =>(name,value)}
}

object ParseJson {

  import books.progInScala.ch31.Json._

  def readJsonFile(fileName: String) = {
    parseAll(value, new FileReader(fileName))
  }
}
