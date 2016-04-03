import scala.language.postfixOps

def isort(list: List[Int]): List[Int] = list match {
  case Nil => Nil
  case x :: xs => insert(x, isort(xs))
}

def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case Nil => x :: xs
  case y :: ys => if (x < y) x :: xs else y :: insert(x, ys)
}
def quickSort(list: List[Int]): List[Int] = list match {
  case Nil => Nil
  case _ => quickSort(list filter (list.head <)) ++ List(list.head) ++ quickSort(list filter (list.head >))
}
val list: List[Int] = List(3, 2, 6, 4, 9, 10, 33, 7)
isort(list)
quickSort(list)

def append[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case Nil => ys
  case x :: xs1 => x :: append(xs1, ys)
}

append(list, (56 to 59).toList)

def mergeSort[T](less: (T, T) => Boolean)(list: List[T]): List[T] = {
  def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
    case (Nil, _) => ys
    case (_, Nil) => xs
    case (x :: xs1, y :: ys1) =>
      if (less(x, y)) x :: merge(xs1, ys) else y :: merge(xs, ys1)
  }

  val n = list.length / 2
  if (n == 0) list
  else {
    val (fst, snd) = list splitAt n
    merge(mergeSort(less)(fst), mergeSort(less)(snd))
  }
}

list map (1 +)

val words = List("the", "quick", "brown", "fox")
("" /: words)(_ + " " + _)
val part = (words.tail foldLeft words.head) _
def reverseList[T](xs: List[T]) = (List[T]() /: xs) { (ys, y) => y :: ys}
list
reverseList(list)
mergeSort((x: Int, y: Int) => x < y)(list)
mergeSort[Int](_ < _)(list)

def mergeSortSwapped[T](list:List[T])(less:(T,T)=>Boolean) = mergeSort(less)(list)
mergeSortSwapped(list)(_ < _)