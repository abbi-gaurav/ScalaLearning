abstract class CCTherms{
  val yearMade:Int
  val bookPrice:Int
  val description:String
  val name:String

  def toXML = <ccTherm>
                <description>{description}</description>
                <yearMade>{yearMade}</yearMade>
                <bookPrice>{bookPrice}</bookPrice>
                <name>{name}</name>
              </ccTherm>
}
val x = {
  <a>
    this is some test
    <atag/>
  </a>
}
val y = <a>
  {{3 + 4}}
</a>
val z = <a>
  {{{"<a> possible wrong </a>"}}}
</a>

val therm = new CCTherms {
  override val description: String = "hot coca #1"
  override val bookPrice: Int = 5
  override val yearMade: Int = 1997
  override val name = "kuchbi"
}
therm.toXML
x.text
val elem = <a><b><c>hello</c><c>hi</c></b><c>jjj</c></a>
elem \ "b"
elem \ "c"
elem \\ "c"
