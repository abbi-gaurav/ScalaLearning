package books.progInScala.ch22

/**
 * Created by gabbi on 24/08/14.
 */
object NQueens extends App {

  def isSafe(column: Int, prevQueens: List[Int]): Boolean = {
    val row = prevQueens.length
    val prevPlacements = prevQueens zip (row - 1 to 0 by -1)
    prevPlacements forall { case (pCol, pRow) => pCol != column && math.abs(row - pRow) != math.abs(column - pCol)}
  }

  def placeQueens(n: Int = 8): Set[List[Int]] = {
    def loop(k: Int): Set[List[Int]] = {
      if (k == 0) Set(List())
      else {
        for {
          prevQueens <- loop(k - 1)
          column <- 0 until n
          if isSafe(column, prevQueens)
        } yield column :: prevQueens
      }
    }
    loop(n)
  }

  def show(queens: List[Int]) = {
    val lines = for {
      n <- queens.reverse
    } yield Vector.fill(queens.length)("* ").updated(n, "X ").mkString
    "\n" + (lines mkString "\n")
  }

  override def main(args: Array[String]) {
    val res = if (args.length > 0) placeQueens(args(0).toInt) else placeQueens()
    (res take 3 map show).foreach(println)
  }
}
