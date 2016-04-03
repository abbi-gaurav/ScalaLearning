import com.learn.fpConcepts.pathDependent.{Keys, AwesomeDB, Franchise}

class A{
  class B
  var b: Option[B] = None
}

val a1 = new A
val a2 = new A

val b1 = new a1.B
val b2 = new a2.B

a2.b = Some(b2)
a1.b = Some(b1)

val starTrek = new Franchise("star-trek")

val starWars = new Franchise("star-wars")

val quark = starTrek.Character("Quark")
val jadzia = starTrek.Character("Jadzia Dax")

val luke = starWars.Character("Luke Skywalker")
val yoda = starWars.Character("Yoda")

starTrek.createFanFictionWith(lovestruck = quark, objectOfDesire = jadzia)
starWars.createFanFictionWith(objectOfDesire = yoda, lovestruck = luke)

def createFightBetween(f:Franchise)(hero:f.Character, villian:f.Character) = (hero, villian)

val ds = new AwesomeDB
ds.set(Keys.foo)(23)
ds.set(Keys.bar)("23")

val v1:Option[Int] = ds.get(Keys.foo)
val v2:Option[String] = ds.get(Keys.bar)