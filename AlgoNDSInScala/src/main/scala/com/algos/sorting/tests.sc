val list = List(2, 10, 6, 14, 1, 9, 5, 0, 4, 8, 12, 13, 3, 11, 7, 15)

import com.algos.sorting.{Selection, Insertion, Bubble, MergeSort}
import com.algos.sorting.QuickSort._
sort(list)

MergeSort.sort(list)

Bubble.sort(list.toArray) mkString " "

Insertion.sort(list)

Selection.sort(list)