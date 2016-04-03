package books.progInScala.ch15

/**
 * Created by gabbi on 16/07/14.
 */
object Express extends App {
  val e1 = BinOp("*", BinOp("/", Num(1), Num(2)),
    BinOp("+", Var("x"), Num(1)))

  val e2 = BinOp("+", BinOp("/", Var("x"), Num(2)),
    BinOp("/", Num(1.5), Var("x")))

  val e3 = BinOp("/", e1, e2)

  def show(e: Expr) = println(ExprFormatter.format(e) + "\n\n")

  for (e <- Array(e1, e2, e3)) show(e)
}

