/**
 * Created by gabbi on 08/12/14.
 */
package object validator {

  type Effect = () => Unit

  def fakeSave : Effect = () => println("fake save")
}
