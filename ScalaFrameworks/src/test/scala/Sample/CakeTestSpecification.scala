package Sample

import javax.persistence.{TypedQuery, EntityManager}

import cakePattern.{UserRepoJPAComponent, DefaultUserServiceComponent, User}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

/**
 * Created by gabbi on 15/12/14.
 */
class CakeTestSpecification extends Specification with Mockito{
  trait MockEntityManager{
    val em = mock[EntityManager]

    def expect(f : EntityManager => Any): Unit ={
      f(em)
    }
  }

  "findAll should use the Entity Manager's Typed queries" in {
    val query = mock[TypedQuery[User]]
    val users = List[User]()

    val userService = new DefaultUserServiceComponent with UserRepoJPAComponent with MockEntityManager

    userService.expect{em =>
      em.createQuery("from User", classOf[User]) returns query
      import scala.collection.JavaConversions._
      query.getResultList returns users
    }

    userService.userService.findAll must_==users
  }
}
