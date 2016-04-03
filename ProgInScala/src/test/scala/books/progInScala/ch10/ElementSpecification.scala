package books.progInScala.ch10

import org.scalacheck.Prop._
import org.scalacheck.{Gen, Properties}

/**
 * Created by gabbi on 14/07/14.
 */
object ElementSpecification extends Properties("Element") {
  property("width") = forAll(Gen.posNum[Int]) { w: Int =>
    Element('x', w, 3).width == w
  }

  property("height") = forAll(Gen.posNum[Int]) { h:Int =>
    Element('x', 2, h).height == h
  }
}
