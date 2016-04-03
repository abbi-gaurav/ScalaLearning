package cakePattern

import javax.persistence.Persistence

/**
 * Created by gabbi on 15/12/14.
 */
object ApplicationLive {
  val userServiceComponent = new DefaultUserServiceComponent with UserRepoJPAComponent{
    val em = Persistence.createEntityManagerFactory("cake.pattern").createEntityManager()
  }

  val userService = userServiceComponent.userService
}
