val opt = Some("x")
val opt2:Option[String] = None
val sb = new StringBuilder("y")
def enrichObj(optS:Option[String]) =  optS.foldLeft(sb){
  case (acc, x) => acc.append(x)
}

enrichObj(opt)
enrichObj(opt2)

val x:Seq[Int] = List(1)
2 +: x