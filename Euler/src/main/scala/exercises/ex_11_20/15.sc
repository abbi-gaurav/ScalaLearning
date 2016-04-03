def routes(gridSize: Int) = {

  def choose(n: Long, k: Long): Long = {
    if (k == 0) 1
    else (n * choose(n - 1, k - 1)) / k
  }

  choose(2 * gridSize, gridSize)
}

routes(20)

//using wikipedia and some articles on this solution
def routes2(k:Int) = {
  val n = 2*k

  (BigInt(n - k + 1) to n).product/(BigInt(1) to k).product
}

routes2(20)