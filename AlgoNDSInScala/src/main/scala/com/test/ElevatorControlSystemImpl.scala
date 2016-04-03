package com.test

/**
 * Created by gabbi on 06/08/14.
 */
class ElevatorControlSystemImpl(numElevators: Int) extends ElevatorControlSystem {
  //initially create an array of specified size with all the elevators at ground floor and no goal floors

  private val elevatorStates: Array[(Int, Set[Int])] = Array.fill(numElevators) {
    (0, Set.empty[Int])
  }

  override def status(): Seq[(Int, Int, Set[Int])] = {
    for {
      n <- 0 until elevatorStates.length
    } yield {
      val (currentFloor, goalFloors) = elevatorStates(n)
      (n, currentFloor, goalFloors)
    }
  }

  override def update(elevatorId: Int, floorNumber: Int, goalFloorNumbers: Set[Int]): Unit = {
    elevatorStates.update(elevatorId, (floorNumber, goalFloorNumbers))
  }

  private def addToGoalFloors(goalFloors: Set[Int], floorNumber: Int, direction: Direction): Set[Int] = direction match {
    case Up =>
      val (fst, snd) = goalFloors.span(_ < floorNumber)
      fst ++ Set(floorNumber) ++ snd
    case Down =>
      val (fst, snd) = goalFloors.span(_ > floorNumber)
      fst ++ Set(floorNumber) ++ snd
  }

  def findDistance(goals: Set[Int], currentFloor: Int, pickUpFloor: Int): Int = {
    if(goals.isEmpty) math.abs(currentFloor - pickUpFloor)
    else {
      val direction = getDirection(goals.head, currentFloor)
      val last: Int = goals.last
      direction match {
        case Up =>
          (last - currentFloor) + (last - pickUpFloor)
        case Down =>
          (currentFloor - last) + (pickUpFloor - last)
      }
    }
  }

  override def pickup(floorNumber: Int, direction: Direction): Unit = {
    val zipWithIndex: Array[((Int, Set[Int]), Int)] = elevatorStates.zipWithIndex
    val elevatorsInSameDirection = zipWithIndex.filter {
      case ((c, gfs), _) => gfs.nonEmpty && getDirection(gfs.head, c) == direction &&
        isYetToCome(direction, floorNumber, c)
    }

    val targetElevator = if (elevatorsInSameDirection.nonEmpty) {
      elevatorsInSameDirection.minBy { case ((currentF, goalFls), _) => math.abs(currentF - floorNumber)}
    } else {
      zipWithIndex.minBy{case ((current, goals), _) => findDistance(goals, current, floorNumber)}
    }

    val ((currentFloor, goalFloors), idx) = targetElevator
    val newGoalFloors = addToGoalFloors(goalFloors, floorNumber, direction)
    update(idx, currentFloor, newGoalFloors)
  }

  override def step() {
    for (
      n <- 0 until elevatorStates.length
    ) {
      val (currentFloor, goalFloors) = elevatorStates(n)
      if(goalFloors.nonEmpty) {
        val newCurrentFloor = if (goalFloors.head < currentFloor) currentFloor - 1 else currentFloor + 1
        update(n, newCurrentFloor, if (newCurrentFloor == goalFloors.head) goalFloors.tail else goalFloors)
      }
    }
  }

  private def getDirection(headGoalFloor: Int, currentFloor: Int) = if (headGoalFloor < currentFloor) Down else Up

  private def isYetToCome(direction: Direction, pickUpFloor: Int, currentFloor: Int) = direction match {
    case Up => pickUpFloor > currentFloor
    case Down => pickUpFloor < currentFloor
  }
}
