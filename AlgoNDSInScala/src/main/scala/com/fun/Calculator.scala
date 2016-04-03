package com.fun

/**
 * Created by gabbi on 27/09/14.
 */
object Calculator {

  def calculate(expression: String): Int = {
    import ExpressionConverter._
    val postFixExpr = infix2PostFix(expression)
    println(postFixExpr)
    0
  }

}
