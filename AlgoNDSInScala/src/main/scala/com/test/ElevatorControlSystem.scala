package com.test

/**
 * Created by gabbi on 06/08/14.
 */
trait ElevatorControlSystem {
  def status(): Seq[(Int, Int, Set[Int])]

  def update(elevatorId: Int, floorNumber: Int, goalFloorNumbers: Set[Int])

  def pickup(floorNumber: Int, direction: Direction)

  def step()
}

trait Direction

case object Up extends Direction

case object Down extends Direction