package com.learn.concurrency.fileMgr

import java.io.File
import java.util.concurrent.LinkedBlockingDeque

import com.learn.concurrency.utils._
import com.learn.concurrency.utils.Logger
import org.apache.commons.io.FileUtils

import scala.annotation.tailrec
import scala.collection.JavaConverters._

/**
 * Created by gabbi on 04/01/15.
 */
class FileSystem(dir: String) {
  val rootDir = new File(dir)
  val files: scala.collection.concurrent.Map[String, Entry] = new scala.collection.concurrent.TrieMap[String, Entry]

  for (f <- FileUtils.iterateFiles(rootDir, null, false).asScala) {
    files.put(f.getName, new Entry(false))
  }

  private val messages = new LinkedBlockingDeque[String]()

  private val logger = new Thread {
    setDaemon(true)

    override def run(): Unit = {
      while (true) Logger.log(messages.take)
    }
  }
  logger.start()

  def allFiles:Iterable[String] = for{
    (name,state) <- files
  }yield {
    name
  }
  def logMessage(msg: String) = messages.offer(msg)

  def deleteFile(fileName: String) = files.get(fileName) match {
    case Some(entry) => execute {
      if (prepareForDelete(entry)) {
        if (FileUtils.deleteQuietly(new File(fileName))) {
          files.remove(fileName)
        }
      }
    }
    case None => logMessage(s"file with name $fileName not found")
  }

  @tailrec final def prepareForDelete(entry: Entry): Boolean = {
    val state: FileState = entry.state.get()

    state match {
      case Creating() => logMessage("creating file, cannot delete"); false
      case Idle() =>
        if (entry.state.compareAndSet(state, Deleting())) {
          true
        } else {
          prepareForDelete(entry)
        }
      case Copying(_) => logMessage("file being copied, cannot delete"); false
      case Deleting() => logMessage("file being deleted already"); false
    }
  }

  @tailrec final def release(entry: Entry): Copying = {
    val state = entry.state.get()
    state match {
      case c@Copying(n) =>
        val newState = if (n == 1) Idle() else Copying(n - 1)
        if (entry.state.compareAndSet(state, newState)) c else release(entry)
      case _ => throw new IllegalStateException("release copy should be called when in copying state")
    }
  }

  @tailrec final def acquire(entry: Entry): Boolean = entry.state.get match {
    case i: Idle =>
      if (entry.state.compareAndSet(i, Copying(1))) {
        true
      } else {
        acquire(entry)
      }
    case oldCopying@Copying(n) =>
      if (entry.state.compareAndSet(oldCopying, Copying(n + 1))) {
        true
      } else {
        acquire(entry)
      }
    case _: Deleting | _: Creating => false
  }

  def copyFile(src: String, dest: String): Unit = files.get(src) match {
    case Some(entry) if !entry.isDir => execute {
      if (acquire(entry)) try {
        val destEntry = new Entry(false)
        destEntry.state.set(new Creating)
        if (files.putIfAbsent(dest, destEntry) == None) try {
          FileUtils.copyFile(new File(src), new File(dest))
        } finally {
          release(destEntry)
        }
      } finally {
        release(entry)
      }
    }
  }
}
