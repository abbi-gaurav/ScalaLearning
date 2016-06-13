package week1.rg.trees

import week1.rg.RGenerator

/**
  * Created by gabbi on 29/05/16.
  */
trait Tree

case class Inner(left: Tree, right: Tree) extends Tree

case class Leaf(x: Int) extends Tree

object Tree {

  import RGenerator._

  def randomLeaf: RGenerator[Leaf] = for (x <- integers) yield Leaf(x)

  def randomInner: RGenerator[Inner] = for {
    l <- randomTree
    r <- randomTree
  } yield Inner(l, r)

  def randomTree: RGenerator[Tree] = for {
    isLeaf <- booleans
    tree <- if (isLeaf) randomLeaf else randomInner
  } yield tree

}
