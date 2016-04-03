package com.learn.fpConcepts.monoid.validator

import validator.Effect

/**
 * Created by gabbi on 08/12/14.
 */
sealed trait Validity {

}

case object Valid extends Validity
case class Invalid(e1:String, en:List[String]) extends Validity

object Utils{
  val nonEmpty = Validator.pred[String](_.nonEmpty, "must be non-empty")
  val lowercase = Validator.regex("""^[a-z]+$""".r.pattern, "must be lowercase")

  val userNameV = nonEmpty  andIfSuccessful lowercase
  val userNameV2 = nonEmpty+lowercase

  def buildErrorMessage(field:String, h:String, t:List[String]):String = t match {
    case Nil => s"$field $h"
    case _ => (h::t).zipWithIndex.map{case (e,i) => s"${i+1}) $e"}.mkString(s"$field",",",".")
  }

  def save(userName:String):Either[String, Effect] = userNameV(userName) match {
    case Valid => Right(validator.fakeSave)
    case Invalid(h,t) => Left(buildErrorMessage("Username", h,t))
  }
}
