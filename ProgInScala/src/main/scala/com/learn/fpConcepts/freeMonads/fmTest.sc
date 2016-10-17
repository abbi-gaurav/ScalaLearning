import cats.free.Free
import com.learn.fpConcepts.freeMonads.Orders
import com.learn.fpConcepts.freeMonads.Orders._

val smartTrade: Free[Orders, Response] = for {
  _ <- buy("SAP", 100)
  _ <- buy("GOOGL", 100)
  resp <- sell("IBM", 100)
} yield resp

smartTrade.foldMap(orderPrinter)

smartTrade.foldMap(xorInterpreter)