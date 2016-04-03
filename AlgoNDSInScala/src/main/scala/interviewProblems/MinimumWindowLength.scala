package interviewProblems

import scala.annotation.tailrec

/**
  * Created by gabbi on 01/01/16.
  */
object MinimumWindowLength extends App {
  def findMinWindowLength(mainString: String, subString: String): Int = {
    val need2Find: Map[Char, Int] = subString.groupBy(identity) map (x => (x._1, x._2.length))
    @tailrec
    def moveBegin(currentBegin: Int, currentHasFound: Map[Char, Int]): (Int, Map[Char, Int]) = {
      val beginCharacter = mainString.charAt(currentBegin)
      val charNotInSearch: Boolean = need2Find.get(beginCharacter).isEmpty
      val excessCharacter: Boolean = !charNotInSearch && currentHasFound(beginCharacter) > need2Find(beginCharacter)
      if (!charNotInSearch && !excessCharacter) {
        (currentBegin, currentHasFound)
      } else {
        val newHasFound = if (excessCharacter) currentHasFound.updated(beginCharacter, currentHasFound(beginCharacter) - 1) else currentHasFound
        moveBegin(currentBegin + 1, newHasFound)
      }
    }

    def moveBeginNUpdate(index: Int, rsWithUpdatedCount: RunningState): RunningState = {
      val (newBegin, newHasFound) = moveBegin(rsWithUpdatedCount.begin, rsWithUpdatedCount.hasFound)
      rsWithUpdatedCount.copy(begin = newBegin, minWindowLength = scala.math.min(rsWithUpdatedCount.minWindowLength, index - newBegin + 1), hasFound = newHasFound)
    }

    val result: RunningState = (0 until mainString.length).foldLeft(RunningState(0, 0, Int.MaxValue, need2Find.keys.map((_, 0)).toMap)) {
      case (rs@RunningState(begin, count, _, hasFound), index) =>
        val character = mainString.charAt(index)
        if (!(need2Find contains character)) {
          rs
        } else {
          val newHasFound = hasFound.updated(character, hasFound(character) + 1)
          val rsWithUpdatedCount = rs.copy(count = if (newHasFound(character) <= need2Find(character)) count + 1 else count, hasFound = newHasFound)

          if (rsWithUpdatedCount.count == subString.length) {
            moveBeginNUpdate(index, rsWithUpdatedCount)
          } else {
            rsWithUpdatedCount
          }
        }
    }
    print(result)
    if (result.count == subString.length) result.minWindowLength else -1

  }

  case class RunningState(begin: Int, count: Int, minWindowLength: Int, hasFound: Map[Char, Int])

  findMinWindowLength("acbbaca", "aba")
}
