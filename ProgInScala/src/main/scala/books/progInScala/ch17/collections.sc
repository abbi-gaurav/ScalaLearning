import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

val lb = ListBuffer(1)
lb += 2
3 +: lb
lb.length
val ab = new ArrayBuffer[Int]()
ab += 2
val x = 1 +: ab
ab.length
x.length

ab(0)

val s = Set(1,2,2,3)
s + 4
s - 3
s ++ List(9,8)
s -- List(2,3)
s & Set(1,2)
Set(1,3) &~ s
s  &~ Set(1,3)

val regex: String = "[ .!,]+"

def countWords(s:String) = {
  val map = mutable.Map.empty[String, Int]
  val strings: Array[String] = s.split(regex)
  for(str <- strings){
    val word = str.toLowerCase
    val oldCount = map.getOrElse(word,0)
    map += (word -> (oldCount+1))
  }
  map
}
val string: String = "See Spot run! Run, Spot. Run!"
countWords(string)

def countWords2(s:String) = s.split(regex).map(_.toLowerCase).groupBy(str=>str).map(x=>(x._1, x._2.length))
countWords2(string)

val set = Set(1,2)
//this will not work
// set += 2

var set2 = Set(1,2)
set2 += 3
set2

val colors = List("blue","green", "red", "yellow")

import scala.collection.immutable.TreeSet
val treeSet = TreeSet[String]() ++ colors

val threadSafeSet = new mutable.HashSet[String]() with mutable.SynchronizedSet