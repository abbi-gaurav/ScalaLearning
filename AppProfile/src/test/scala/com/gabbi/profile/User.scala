package com.gabbi.profile

import com.gabbi.profile.TestHelper._
import io.gatling.core.Predef._
import io.gatling.http.Predef._


/**
  * Created by gabbi on 13/06/16.
  */
object User {
  private val feeder = Iterator.continually(
    Map("email" -> s"${randomString(10)}@testmail.com")
  )

  private[profile] val createUser = feed(feeder).exec(http("createUser")
    .post("/users")
    .body(StringBody(
      """
        |{
        |"email" : "${email}"
        |}
      """.stripMargin
    )).asJSON
  )
}
