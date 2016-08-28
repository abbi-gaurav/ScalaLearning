import scala.language.higherKinds
import scalaz.syntax.Ops
import scala.language.postfixOps
/**
  * ((* -> *) -> *)
  *
  * @tparam F
  */
trait Functor[F[_]] {
  self =>
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

trait FunctorOps[F[_], A] extends Ops[F[A]] {
  implicit def F: Functor[F]

  final def map[B](f: A => B): F[B] = F.map(self)(f)
}

import scalaz._
import Scalaz._
val f = ((x:Int) => x+1) map (_ * 7)

f(2)

val list: List[Int] = List(1, 2, 3)
list >| "x"
list as "x"

list fpair

list strengthL "x"

list strengthR "x"

list void

val x = list map ((_:Int) * (_:Int)).curried
x map {_(9)}
