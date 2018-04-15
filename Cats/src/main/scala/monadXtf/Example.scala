package monadXtf

import cats.data.OptionT

import scala.concurrent.Future

/**
  * Created by gabbi on 02.07.17.
  */
class Example {

}

object DBOps {
  //Future[Option[User]]
  def findUserById(id: Long): OptionT[Future, User] = ???

  def findAddressByUser(user: User): OptionT[Future, Address] = ???

  def findAddressByUserId(id:Long): OptionT[Future, Address] = for{
    user <- findUserById(id)
    address <- findAddressByUser(user = user)
  }yield address
}

case class User()

case class Address()
