println("Welcome to the Scala worksheet")

val list = List(1,2,3,4,5,6)
list partition (_ % 2 == 0)
list span (_ < 3)

import books.progInScala.initial.checkSumCalculator
import checkSumCalculator._
calculate("Life is great")
calculate("I am learning scala")
val ss = "of love".split(" ").toList
val css = ss map (str => (str,calculate(str)))

val d=1.2
val f =1.2f

println("""|welcome to programming in scala.
          						|try "online" for doubts""".stripMargin)

def updateRecord(s:Symbol, value:Any){

}
val s = 'asSymbol
updateRecord('asSymbol, 1)
updateRecord(s, 2)

val u = -2.0
2.0.unary_-
case class Str(s:String){
  def unary_!() = Str(s.reverse)
}
val str = Str("this is it")
val rev = !str

val check = null == null
val check2 = null == "this"
val check3 = "this" == null