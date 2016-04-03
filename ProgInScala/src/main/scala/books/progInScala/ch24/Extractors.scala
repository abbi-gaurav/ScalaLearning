package books.progInScala.ch24

/**
 * Created by gabbi on 30/08/14.
 */
object Extractors extends App {

  object Email extends ((String, String) => String) {

    override def apply(user: String, domain: String) = user + "@" + domain

    def unapply(email: String) = {
      val parts = email split "@"
      if (parts.length == 2) Some(parts(0), parts(1)) else None
    }
  }

  object Twice {
    def apply(s: String) = s + s

    def unapply(s: String): Option[String] = {
      val length = s.length / 2
      val half = s.substring(0, length)
      if (half == s.substring(length)) Some(half) else None
    }
  }

  object Uppercase {
    def unapply(s: String): Boolean = s.toUpperCase == s
  }

  def getInfo(str: String): String = str match {
    case Email(user, domain) => s"user $user, domain $domain"
    case _ => "not a valid email"
  }

  def userTwiceUpper(s: String) = s match {
    case Email(Twice(x@Uppercase()), domain) => "match " + x + " in domain " + domain
    case _ => "do not match"
  }

  private val email: String = "abbi.gaurav@gmail.com"
  println(getInfo(email))
  println(getInfo("shwethavshenoy"))

  println(userTwiceUpper("GG@gmail.com"))
  println(userTwiceUpper("gg@gmail.com"))


  object Domain {
    def apply(strings: String*) = strings.reverse.mkString(".")

    def unapplySeq(domain: String): Option[Seq[String]] = Some(domain.split("\\.").reverse)
  }

  def isUserInDomain(email: String, user: String, domain: String) = email match {
    case Email(usr, Domain(dom, _*)) => user == usr && dom == domain

    case _ => false
  }

  println(isUserInDomain(email, "abbi.gaurav", "com"))

  object ExpandedEmail {
    def unapplySeq(email: String): Option[(String, Seq[String])] = {
      val parts = email split "@"
      if (parts.length == 2) Some(parts(0), parts(1).split("\\.").reverse.toList)
      else None
    }
  }

  val s = "tom@support.epfl.ch"
  //variable binding
  val ExpandedEmail(name, topDomain, subDomains @ _*) = s
  println(name)
  println(topDomain)
  println(subDomains)
}
