/**
 * Surprisingly there are only three numbers that can
 * be written as the sum of fourth powers of their digits:
 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
 * As 1 = 1^4 is not a sum it is not included.
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 * Find the sum of all the numbers that can be written as the
 * sum of fifth powers of their digits.
 */
def max(d:Int) = math.pow(10,d).toInt - 1

def sum(x:Int) = x.toString.map(_.asDigit).map(math.pow(_,5)).sum

val limit = Stream.from(1).find(d => max(d) > sum(max(d))).get

val res = (2 to max(limit)).view.filter(x => x == sum(x)).sum

