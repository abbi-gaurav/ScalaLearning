package books.progInScala.ch20

/**
 * Created by gabbi on 04/08/14.
 */
object Enums extends App{

  object Color extends Enumeration{
    val Red, Green, Yellow = Value
  }

  object Direction extends Enumeration{
    val North = Value("N")
    val South = Value("S")
    val East = Value("E")
    val West = Value("W")
  }

  Direction.values foreach println

  Color.values foreach print

  for(d <- Direction.values) print(d+" ")
}
