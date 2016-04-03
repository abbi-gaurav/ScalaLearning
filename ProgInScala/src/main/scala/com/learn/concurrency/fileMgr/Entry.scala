package com.learn.concurrency.fileMgr

import java.util.concurrent.atomic.AtomicReference

/**
 * Created by gabbi on 04/01/15.
 */
class Entry(val isDir:Boolean) {
  val state = new AtomicReference[FileState](Idle())
}
