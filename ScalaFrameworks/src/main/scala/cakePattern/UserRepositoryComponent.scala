package cakePattern

import javax.persistence.EntityManager
import scala.collection.JavaConversions._

/**
 * Created by gabbi on 15/12/14.
 */
trait UserRepositoryComponent {
  def userLocator:UserLocator
  def userUpdater:UserUpdater

  trait UserLocator{
    def findAll : Seq[User]
  }

  trait UserUpdater{
    def save(user:User)
  }
}

trait UserRepoJPAComponent extends UserRepositoryComponent{
  def em:EntityManager
  override def userLocator = new UserLocatorJPA(em)
  override def userUpdater = new UserUpdaterJPA(em)

  class UserLocatorJPA(em:EntityManager) extends UserLocator{

    override def findAll: Seq[User] = {
      em.createQuery("from User", classOf[User]).getResultList
    }
  }

  class UserUpdaterJPA(em:EntityManager) extends UserUpdater{
    override def save(user: User): Unit = em.persist(user)
  }
}

trait UserServiceComponent{
  def userService:UserService

  trait UserService{
    def findAll : Seq[User]
    def save(user:User)
  }
}

trait DefaultUserServiceComponent extends UserServiceComponent{
  this:UserRepositoryComponent =>

  def userService:UserService = new DefaultUserService

  class DefaultUserService extends UserService{
    override def findAll: Seq[User] = userLocator.findAll

    override def save(user: User): Unit = userUpdater.save(user)
  }
}

case class User(name:String)
