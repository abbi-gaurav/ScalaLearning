import com.learn.fpConcepts.hof.fds.Tree._
import com.learn.fpConcepts.hof.fds.{Branch, Leaf, Tree}

val tree: Tree[Int] = Branch(Branch(Leaf(1), Leaf(4)), Leaf(3))

tree.size
tree.sizeFold

maximum(tree)

maximumFold(tree)

tree.depth
tree.depthFold

tree map (_.toString)
tree map (_.toString)

