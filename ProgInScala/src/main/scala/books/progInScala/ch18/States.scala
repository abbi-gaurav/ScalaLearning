package books.progInScala.ch18

/**
 * Created by gabbi on 02/08/14.
 */
class States {
}

class Time{
  private [this] var h = 12
  private [this] var m = 60

  def hour:Int = h
  def hour_= (x:Int){
    require( 0 <= x && x < 24)
    h = x
  }

  def minute:Int = m
  def minute_=(x:Int){
    require(0 <= x && x < 60)
    m = x
  }
}
