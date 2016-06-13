package com.gabbi.profile

import io.gatling.core.Predef._
import io.gatling.core.session.Expression
import io.gatling.http.Predef._

import scala.concurrent.duration._

/**
  * Created by gabbi on 13/06/16.
  */
class BasicSimulation extends Simulation {
  private val AppSlashJson: Expression[String] = "application/json"

  private val httpConf = http
    .baseURL("http://localhost:9000")
    .acceptHeader(AppSlashJson)
    .doNotTrackHeader("1")
    .maxRedirects(2)
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  private val scenarioBuilder = scenario("Create User").exec(User.createUser).exec()

  val setUpRes: SetUp = setUp(scenarioBuilder.inject(rampUsers(10) over (10 seconds))).protocols(httpConf)
}
