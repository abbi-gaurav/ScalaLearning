package com.learn.fpConcepts.composition

/**
  * Created by gabbi on 03/01/16.
  */
object Understanding {
  type Money = Double

  case class User(name: String, salaryNet: Money)

  type Database = Map[Int, User]

  val database: Database = Map(
    1 -> User("ss", 10.0),
    2 -> User("gg", 100),
    3 -> User("sa", 90)
  )

  val lookup: Database => Int => Option[User] = db => key => db.get(key)

  val salary: User => Money = _.salaryNet

  val net: Money => Money = _ * 1.23

  val pureComposition: (User) => Money = net compose salary

  val composedLogicWithOption: (Int) => Option[Money] = key => {
    lookup(database)(key) map pureComposition
  }

  case class HttpMessage(code: Int, msg: String)

  val error: HttpMessage = HttpMessage(404, "really sorry")

  val success: (Money) => HttpMessage = (money: Money) => HttpMessage(200, s"netto is $money")
}
