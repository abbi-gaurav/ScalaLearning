import books.progInScala.ch20.{Swiss, US}

val tenDollar = US.make(1000)
val twoDollar = US.make(200)
tenDollar + twoDollar

val tenFranc = Swiss.make(2000)
val oneFranc = Swiss.make(100)
oneFranc + tenFranc

US.Dollar from Swiss.Franc from tenDollar