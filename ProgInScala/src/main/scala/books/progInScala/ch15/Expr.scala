package books.progInScala.ch15

/**
 * Created by gabbi on 16/07/14.
 */
sealed abstract class Expr {

  def simplifyAll: Expr = this match {
    case UnOp("-", UnOp("-", e)) => e.simplifyAll
    case BinOp("+", e, Num(0)) => e.simplifyAll
    case BinOp("*", e, Num(1)) => e.simplifyAll
    case UnOp(op, e) => UnOp(op, e.simplifyAll)
    case BinOp(op, l, r) => BinOp(op, l.simplifyAll, r.simplifyAll)
    case _ => this
  }

  def describe: String = this match {
    case Num(_) => "a number"
    case Var(_) => "a variable"
    case _ => "an expr"
  }

}

case class Var(s: String) extends Expr

case class Num(n: Double) extends Expr

case class UnOp(operator: String, e: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object ExprFormatter {
  private val opGroups = Array(
    Set("|", "||"),
    Set("&", "&&"),
    Set("^"),
    Set("==", "!="),
    Set("<", "<=", ">", ">="),
    Set("+", "-"),
    Set("*", "%")
  )

  private val precedence:Map[String,Int] = {
    val associations = for {
      i <- 0 until opGroups.length
      op <- opGroups(i)
    } yield op -> i
    Map() ++ associations
  }

  private val unaryPrecedence = opGroups.length
  private val fractionalPrecedence = -1

  import books.progInScala.ch10._

  private def format(e: Expr, enclPrecedence: Int): Element = e match {
    case Var(name) => Element(name)
    case Num(num) =>
      def stripDot(s: String) = if (s endsWith ".0") s.init.init else s
      Element(stripDot(num.toString))
    case UnOp(op, arg) => Element(op) beside format(arg, unaryPrecedence)
    case BinOp("/", left, right) =>
      val top = format(left, fractionalPrecedence)
      val bot = format(right, fractionalPrecedence)
      val line = Element('-', top.width max bot.width, 1)
      val fraction = top above line above bot
      if (enclPrecedence != fractionalPrecedence) fraction else Element(" ") beside fraction beside Element(" ")
    case BinOp(opt, left, right) =>
      val oprPrecedence: Int = precedence(opt)
      val l = format(left, oprPrecedence)
      val r = format(right, oprPrecedence)
      val operation = l beside Element(" " + opt + " ") beside r
      if (enclPrecedence <= oprPrecedence) operation else Element("(") beside operation beside Element(")")
  }

  def format(e:Expr):Element = format(e,0)
}