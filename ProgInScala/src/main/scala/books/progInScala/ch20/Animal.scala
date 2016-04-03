package books.progInScala.ch20

/**
 * Created by gabbi on 04/08/14.
 */
class Food

abstract class Animal {
  type SuitableFood <: Food

  def eat(food: SuitableFood) {
    println("eating "+food)
  }
}

case class Grass() extends Food

class Cow extends Animal {
  type SuitableFood = Grass
}

case class Fish() extends Food

object Test extends App {
  private val cow: Cow = new Cow()
  private val grass: Grass = new Grass()
  cow eat grass
  //  cow eat fish
}
