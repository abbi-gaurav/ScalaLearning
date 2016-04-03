def generalSize(x:Any) = x match {
  case s:String => s.length
  case m:Map[_,_] => m.size
  case _ => -1
}

generalSize("abc")
generalSize(Map(1->'a'))
generalSize(Math.PI)
generalSize(null)

val capitals =
  Map("France" -> "Paris", "Japan" -> "Tokyo")

def show(x:Option[Any]):String = x match {
  case Some(value) => value.toString
  case None => "?"
}

show (capitals get "France")
show (capitals get "India")

val withDefault : Option[Int] => Int = {
  case Some(x) => x
  case None => throw new IllegalStateException("no value")
}

for ((country,capital)<-capitals)yield "the capital of "+country +" is "+capital

val results = List(Some("apple"), None,Some("orange"))

for(Some(fruit) <- results) println(fruit)