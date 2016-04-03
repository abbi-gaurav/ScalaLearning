import scala.io.Source

//21
/**
Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.

For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284
are 1, 2, 4, 71 and 142; so d(284) = 220.
Evaluate the sum of all the amicable numbers under 10000.
  * */
def sumOfEvenDivisors(num: Int) = (1 to num / 2).filter(num % _ == 0).sum

def isAmicable(a: Int): Boolean = {
  val b = sumOfEvenDivisors(a)
  a != b && sumOfEvenDivisors(b) == a
}

(1 until 10000).filter(isAmicable).sum

//22
/**
Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing
over five-thousand first names, begin by sorting it into
alphabetical order. Then working
out the alphabetical value for each name, multiply this value by
its alphabetical position in the list to obtain a name score.

For example, when the list is sorted into alphabetical order,
COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53,
is the 938th name in the list. So, COLIN would obtain a score of
938 × 53 = 49714.
What is the total of all the name scores in the file?
  */
//important is look for list kind of operations to convert "A" ==> A
val fileName: String = "/Users/gabbi/learning/coursera/scala/intelliJIdeaWS/Euler/src/main/resources/names.txt"
val names = Source.fromFile(fileName).mkString.split(",").toList.map(_.init.tail)
names.sorted.zipWithIndex.map{ case (str, idx) => (idx+1) * str.map(_ - 64).sum }.sum
