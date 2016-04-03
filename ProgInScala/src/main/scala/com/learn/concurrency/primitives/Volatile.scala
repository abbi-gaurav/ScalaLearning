package com.learn.concurrency.primitives

/**
 * Created by gabbi on 31/12/14.
 */

import com.learn.concurrency.utils._

class Page(val txt: String, var pos: Int)

object Volatile extends App {
  val pages = for (i <- 1 to 5) yield new Page("Na" * (100 - 20 * i) + "Batman!", -1)

  @volatile var found = false

  for (p <- pages) yield thread {
    var i = 0
    while (i < p.txt.length && !found) {
      if (p.txt(i) == '!') {
        p.pos = i
        found = true
      } else {
        i = i + 1
      }
    }
  }

  while (!found) {}

  import com.learn.concurrency.utils.Logger._

  log(s"results ${pages map (_.pos)}")
}
