package lazyEvaluation.waterPouring

/**
  * Created by gabbi on 19/06/16.
  */
class Pouring(capacity: Vector[Int]) {

  //states
  type State = Vector[Int]
  val initialState: Vector[Int] = capacity map (x => 0)

  //moves
  trait Move {
    def change(state: State): State
  }

  case class Empty(glass: Int) extends Move {
    override def change(state: State): State = state updated(glass, 0)
  }

  case class Fill(glass: Int) extends Move {
    override def change(state: State): State = state updated(glass, capacity(glass))
  }

  case class Pour(from: Int, to: Int) extends Move {
    override def change(state: State): State = {
      val amount: Int = state(from) min (capacity(to) - state(to))
      state updated(from, state(from) - amount) updated(to, state(to) + amount)
    }
  }

  val glasses: Range = capacity.indices

  val moves: Seq[Move] = (for (g <- glasses) yield Empty(g)) ++
    (for (g <- glasses) yield Fill(g)) ++
    (for (from <- glasses; to <- glasses if from != to) yield Pour(from, to))

  //paths
  class Path(history: List[Move], val endState: State) {
    //    def endState: State = history.foldRight(initialState) { case (move, currentState) => move change currentState }

    def extend(move: Move) = new Path(move :: history, move change endState)

    override def toString: String = (history.reverse mkString " ") + "---->" + endState
  }

  val initialPath: Path = new Path(Nil, initialState)

  def from(paths: Set[Path], explored: Set[State]): Stream[Set[Path]] = {
    if (paths.isEmpty) Stream.empty
    else {
      val more: Set[Path] = for {
        path: Path <- paths
        next: Path <- moves map path.extend
        if !(explored contains next.endState)
      } yield next
      paths #:: from(more, explored ++ (more map (_.endState)))
    }
  }

  val pathSets: Stream[Set[Path]] = from(Set(initialPath), Set(initialState))

  def solution(target: Int): Stream[Path] = for {
    pathSet: Set[Path] <- pathSets
    path: Path <- pathSet
    if path.endState contains target
  } yield path
}
