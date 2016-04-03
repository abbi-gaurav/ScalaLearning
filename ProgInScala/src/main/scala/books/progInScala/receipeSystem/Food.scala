package books.progInScala.receipeSystem

/**
 * Created by gabbi on 22/09/14.
 */
abstract class Food(val name: String) {
  override def toString = name
}

abstract class Recipe(val name: String, val ingredients: List[Food], val instructions: String) {
  override def toString = name
}

object Apple extends Food("apple")

object Orange extends Food("orange")

object Cream extends Food("cream")

object Sugar extends Food("sugar")

trait FoodCategories {
  case class FoodCategory(name:String, food:List[Food])
  def allCategories : List[FoodCategory]
}

trait SimpleFoods{
  object Pear extends Food("pear")
  def allFoods = List(Apple, Pear)
  def allCategories = Nil
}

trait SimpleRecipes{
  this:SimpleFoods =>
  object FruitSalad extends Recipe("fruit salad", List(Apple, Pear), "Stir it all together")

  def allRecipes = List(FruitSalad)
}

abstract class Database extends FoodCategories{
  def allFoods:List[Food]
  def allRecipes:List[Recipe]

  def foodName(name:String) = allFoods.find(_.name == name)
}

object SimpleDatabase extends Database with SimpleFoods with SimpleRecipes

abstract class Browser {
  val database : Database

  def recipeUsing(food : Food) = database.allRecipes.filter(_.ingredients.contains(food))

  def displayCategory(category: database.FoodCategory): Unit ={
    println(category)
  }
}

object SimpleBrowser extends Browser{
  val database = SimpleDatabase
}
