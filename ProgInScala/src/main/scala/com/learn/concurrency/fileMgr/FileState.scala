package com.learn.concurrency.fileMgr

/**
 * Created by gabbi on 04/01/15.
 */
sealed trait FileState {
}

case class Idle() extends FileState
case class Creating() extends FileState
case class Deleting() extends FileState
case class Copying(n:Int) extends FileState
