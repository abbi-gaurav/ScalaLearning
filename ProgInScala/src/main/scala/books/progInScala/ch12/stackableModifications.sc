import scala.collection.mutable.ArrayBuffer

abstract class IntQueue {
  def put(x: Int): Unit

  def get: Int
}

class BasicQueue extends IntQueue {
  private val queue = new ArrayBuffer[Int]

  override def put(x: Int): Unit = queue += x

  override def get: Int = queue.remove(0)
}

trait Doubling extends IntQueue {
  abstract override def put(x: Int) = super.put(2 * x)
}

trait Filtering extends IntQueue{
  abstract override def put(x:Int) = {
    if(x >= 0) super.put(x)
  }
}
trait Incrementing extends IntQueue{
  abstract override def put(x:Int) = super.put(x+1)
}
val x = new BasicQueue
x.put(10)
x.put(20)

x.get
x.get

val y = new BasicQueue with Doubling
y.put(10)
y.put(20)

y.get
y.get

val z = new BasicQueue with Incrementing with Filtering
z.put(-1)
z.put(0)
z.put(1)

z.get
z.get

val a = new BasicQueue with Filtering with Incrementing
a.put(-1)
a.put(0)
a.put(1)

a.get
a.get
a.get
