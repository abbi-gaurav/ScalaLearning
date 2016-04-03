import books.progInScala.ch12.Rectangle

trait Philosophical{
  def philosophize(){
    println("I consume memory, therefore I am")
  }
}
trait HasLegs
class Animal
class Frog extends Animal with Philosophical with HasLegs{
  override def toString = "green"

  override def philosophize(){
    println("It ain't easy being "+toString)
  }
}
val frog:Philosophical = new Frog
frog.philosophize()
val x:Animal = new Frog
val y:HasLegs = new Frog

val r = new Rectangle(new books.progInScala.ch12.Point(1,2), new books.progInScala.ch12.Point(4,5))
r.width