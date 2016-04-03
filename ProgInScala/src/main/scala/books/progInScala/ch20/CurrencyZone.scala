package books.progInScala.ch20

/**
 * Created by gabbi on 15/08/14.
 */
abstract class CurrencyZone {
  type Currency <: AbstractCurrency

  def make(x: Long): Currency

  abstract class AbstractCurrency {
    val amount: Long

    def designation: String

    def +(that: Currency): Currency = make(this.amount + that.amount)

    private def decimals(n: Long): Int = if (n == 1) 0 else 1 + decimals(n / 10)

    override def toString = (amount.toDouble / CurrencyUnit.amount.toDouble) formatted
      ("%." + decimals(CurrencyUnit.amount) + "f") + " " + designation

    def from(other: CurrencyZone#AbstractCurrency): Currency = {
      make(math.round(other.amount.toDouble * Converter.exchangeRate(other.designation)(this.designation)))
    }
  }

  val CurrencyUnit: Currency


}

object Swiss extends CurrencyZone {

  abstract class CHF extends AbstractCurrency {
    override def designation = "CHF"
  }

  type Currency = CHF

  def make(rappen: Long): CHF = new CHF {
    override val amount: Long = rappen
  }

  val Rappen = make(1)
  val Franc = make(100)

  val CurrencyUnit = Franc
}

object US extends CurrencyZone {

  abstract class Dollar extends AbstractCurrency {
    override def designation = "USD"
  }

  type Currency = Dollar

  def make(cents: Long): Dollar = new Dollar {
    override val amount: Long = cents
  }

  val Cent = make(1)
  val Dollar = make(100)

  val CurrencyUnit = Dollar
}

object Converter {
  var exchangeRate = Map(
    "USD" -> Map("USD" -> 1.0, "EUR" -> 0.7596,
      "JPY" -> 1.211, "CHF" -> 1.223),
    "EUR" -> Map("USD" -> 1.316, "EUR" -> 1.0,
      "JPY" -> 1.594, "CHF" -> 1.623),
    "JPY" -> Map("USD" -> 0.8257, "EUR" -> 0.6272,
      "JPY" -> 1.0, "CHF" -> 1.018),
    "CHF" -> Map("USD" -> 0.8108, "EUR" -> 0.6160,
      "JPY" -> 0.982, "CHF" -> 1.0)
  )
}