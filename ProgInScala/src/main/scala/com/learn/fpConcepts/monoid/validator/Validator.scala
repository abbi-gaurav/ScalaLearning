package com.learn.fpConcepts.monoid.validator

import java.util.regex.Pattern

/**
 * Created by gabbi on 08/12/14.
 */
case class Validator[A](f : A => Validity) {
  @inline final def apply(a:A) = f(a)

  def +(v:Validator[A]) = Validator[A](a =>
    (apply(a), v(a)) match {
      case (Valid, Valid) => Valid
      case (Valid, e @ Invalid(_,_)) => e
      case (e @ Invalid(_,_), Valid) => e
      case (Invalid(e1, en), Invalid(e2, em)) => Invalid(e1, en ::: e2 :: em)
    }
  )

  def andIfSuccessful(v:Validator[A]) = Validator[A](a =>
    apply(a) match {
      case Valid => v(a)
      case e @ Invalid(_,_) => e
    }
  )
}

object Validator{
  def id[A] = Validator[A](_ => Valid)

  def pred[A](f:A=>Boolean, err: => String) = Validator[A](a => if (f(a)) Valid else Invalid(err, Nil))

  def regex(r:Pattern, err: => String) = pred[String](a => r.matcher(a).matches, err)
}
