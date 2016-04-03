package books.progInScala.ch9

import java.io.File

/**
 * Created by gabbi on 30/06/14.
 */
object FileMatcher {
  private def filesHere = new File(".").listFiles

  def filesMatching(matcher: String => Boolean) {
    for (file <- filesHere; if matcher(file.getName)) yield file
  }

  def filesEnding(query: String) = filesMatching(_.endsWith(query))

  def filesContaining(query: String) = filesMatching(_.contains(query))

  def filesRegex(regex: String) = filesMatching(_.matches(regex))
}
