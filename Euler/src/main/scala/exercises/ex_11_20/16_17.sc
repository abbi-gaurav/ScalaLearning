import scala.collection.immutable.Range.Inclusive

//problem 16
def sumOfDigits(x: Int, exp: Int) = {
  BigInt(x).pow(1000).toString().foldLeft(0)(_ + _.asDigit)
}
sumOfDigits(2, 1000)
//problem 17
/**
 * If the numbers 1 to 5 are written out in words: one, two, three,
 * four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used
 * in total
 * If all the numbers from 1 to 1000 (one thousand) inclusive were
 * written out in words, how many letters would be used?
 * NOTE: Do not count spaces or hyphens. For example, 342
 * (three hundred and forty-two) contains 23 letters and 115
 * (one hundred and fifteen) contains 20 letters.
 * The use of "and" when writing out numbers is in compliance
 * with British usage.
 */
val units = List(0, 3, 3, 5, 4, 4, 3, 5, 5, 4, 3, 6, 6, 8, 8, 7, 7, 9, 8, 8)

val tens = List(0, 0, 6, 6, 5, 5, 5, 7, 6, 6)

def lengthOfStringRep(n:Int):Int = n match {
  case i if i < 20 => units(i)
  case i if i < 100 => tens(i/10) + lengthOfStringRep(i%10)
  case i if i < 1000 => units(i/100) + 7 + (if (i%100 > 0) 3 + lengthOfStringRep(i%100) else 0)
  case 1000 => 11
}
val range: Inclusive = 1 to 1000
val s = range.map(lengthOfStringRep).sum
range.foldLeft(0)((acc, x) => acc + lengthOfStringRep(x))


