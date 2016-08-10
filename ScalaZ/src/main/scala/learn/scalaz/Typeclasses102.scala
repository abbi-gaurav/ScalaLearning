package learn.scalaz

/**
  * Created by gabbi on 10/08/16.
  */
object Typeclasses102 {
  import scala.language.implicitConversions

  trait CanTruthy[A] {
    def truthys(a: A): Boolean
  }

  object CanTruthy {
    def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev

    def truthys[A](f: A => Boolean) = new CanTruthy[A] {
      override def truthys(a: A): Boolean = f(a)
    }
  }

  trait CanTruthyOps[A] {
    def self: A

    implicit def F: CanTruthy[A]

    final def truthy: Boolean = F.truthys(self)
  }

  object ToCanIsTruthyOps {
    implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) = new CanTruthyOps[A] {
      override def self: A = v

      override implicit def F: CanTruthy[A] = ev
    }
  }

  //implicit val intCanTruthy:CanTruthy[Int] = new CanTruthy[Int] {
  //  override def truthys(a: Int): Boolean = a match {
  //    case 0 => false
  //    case _ => true
  //  }
  //}

  implicit val intCanTruthy2: CanTruthy[Int] = CanTruthy.truthys({
    case 0 => false
    case _ => true
  })

  implicit def listCanTruthy[A]: CanTruthy[List[A]] = CanTruthy.truthys{
    case Nil => false
    case _ => true
  }

  implicit val nilCanTruthy: CanTruthy[scala.collection.immutable.Nil.type] = CanTruthy.truthys(_ => false)

  import ToCanIsTruthyOps._

  def canTruthyIf[A:CanTruthy, B, C](cond:A)(ifYes: => B)(ifNo: => C): Any ={
    if(cond.truthy) ifYes else ifNo
  }

  1.truthy
  List("ss").truthy
  (Nil: List[Int]).truthy
  Nil.truthy

}
