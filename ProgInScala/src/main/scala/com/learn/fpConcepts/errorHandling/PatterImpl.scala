package com.learn.fpConcepts.errorHandling

import java.util.regex.Pattern

import com.learn.fpConcepts.errorHandling.OptionLifters._

import scala.util.Try

/**
  * Created by gabbi on 15/05/16.
  */
class PatterImpl {
  def pattern(regex: String): Option[Pattern] = Option(regex).flatMap(x => Try(Pattern.compile(x)).toOption)

  def mkMatcher(pat: String): Option[String => Boolean] = for {
    p <- pattern(pat)
  } yield (s: String) => p.matcher(s).matches()

  def doesMatch(pat: String, s: String): Option[Boolean] = for {
    m <- mkMatcher(pat)
  } yield m(s)

  def bothMatch(pat1: String, pat2: String, string: String) = for {
    f <- mkMatcher(pat1)
    g <- mkMatcher(pat2)
  } yield f(string) && g(string)

  def bothMatch_2(pat1: String, pat2: String, s: String): Option[Boolean] = {
    map2(mkMatcher(pat1), mkMatcher(pat2))((f, g) => f(s) && g(s))
  }
}
