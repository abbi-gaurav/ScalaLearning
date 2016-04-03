package com.fun

/**
 * Created by gabbi on 10/05/15.
 */
object ExpressionConverter extends App{
  private val precedenceMap = Map("*" -> 8, "/" -> 8, "%" -> 8, "+" -> 7, "-" -> 7)

  private def isInvalid(strings: Seq[String]): Boolean = {
    def loop(yetToParse: Seq[String], count: Int): Int = yetToParse match {
      case Nil => count
      case "(" :: xs => loop(xs, count + 1)
      case ")" :: xs => if (count == 0) -1 else loop(xs, count - 1)
      case x :: xs => loop(xs, count)
    }
    loop(strings, 0) != 0
  }

  def infix2PostFix(exprssion: String): List[String] = {
    infixToPostFix(exprssion split " " toList)
  }

  private def infixToPostFix(expressions: Seq[String]): List[String] = {
    if (isInvalid(expressions)) fail(expressions)

    def loop(yetToParse: Seq[String], stack: List[String], acc: List[String]): List[String] = yetToParse match {
      //empty the stack and append all operators that are left
      case Nil => stack match {
        case Nil => acc.reverse
        case x :: xs => {
          loop(yetToParse, xs, x :: acc)
        }
      }

      //put the integer to postfix
      case x :: xs if x forall Character.isDigit => {
        loop(xs, stack, x :: acc)
      }

      //put the start parentheses onto the stack
      case (x@"(") :: xs => {
        loop(xs, x :: stack, acc)
      }

      //when ) is encountered, extract all operators until "(" is reached and append to postfix
      case ")" :: xs => stack match {
        case Nil => fail(yetToParse)
        case "(" :: ys =>{
          loop(xs, ys, acc)
        }
        case y :: ys => {
          loop(yetToParse, ys, y :: acc)
        }
      }

      //based on precedence, either take out the operator from stack and append to postfix or add to stack
      case x :: xs if precedenceMap contains x => stack match {
        case Nil => loop(xs, x :: stack, acc)
        case "(" :: ys => loop(xs, x :: stack, acc)
        case y :: ys =>
          if (precedenceMap(x) <= precedenceMap(y)) {
            loop(yetToParse, ys, y :: acc)
          } else {
            loop(xs, x :: stack, acc)
          }
      }

      case _ => fail(yetToParse)
    }
    loop(expressions, Nil, Nil)
  }

  private def fail(seq:Seq[String]): Nothing = {
    throw new IllegalArgumentException(s"wrong expression ${seq}")
  }

  private def toPostFixString(expression:String):String = infix2PostFix(expression) mkString " "

//  println(toPostFixString("5 + ( ( 1 + 2 ) * 4 ) - 3"))
  println(toPostFixString("2 + 3 * 4"))
}
