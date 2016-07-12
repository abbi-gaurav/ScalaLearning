import com.learn.fpConcepts.laziness.StreamOps._

import scala.Stream._
import scala.language.postfixOps

val stream = from(0)

stream.uMap(_ * 2) take 10 toList2

stream.uTake(10) toList2

stream.uTakeWhile(_ < 5) toList2

stream.uZip(from(3)) uTake 10 toList2

(1 to 3).toStream.uZipAll((3 to 4).toStream).toList2

val s = 1 to 10 toStream
val sub = 4 to 7 toStream
val sub2 = 9 to 11 toStream

import com.learn.fpConcepts.laziness.StreamUtils._
uHasSubSeq(s,sub)
uHasSubSeq(s,sub2)
startsWith(s,sub)

sub.uTails toList2
