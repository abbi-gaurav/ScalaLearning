package books.progInScala.ch25

import scala.beans.{BooleanBeanProperty, BeanProperty}

/**
 * Created by gabbi on 04/09/14.
 */
@SerialVersionUID(123456)
class Data extends Serializable{
  @volatile
  var shared:Int = _

  @transient
  var platformId:String = _

  @deprecated
  def badMethod() = ???

  @BeanProperty
  var size:Int = _

  @BooleanBeanProperty
  var valid:Boolean = _
}
