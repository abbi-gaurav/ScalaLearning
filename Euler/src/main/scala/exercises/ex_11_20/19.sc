/**
 * You are given the following information, but you may prefer to
 * do some research for yourself.
 * 1 Jan 1900 was a Monday. Thirty days has September, April, June and November.
 * All the rest have thirty-one, Saving February alone, Which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless it
 * is divisible by 400.
 * How many Sundays fell on the first of the month during the twentieth century
 * (1 Jan 1901 to 31 Dec 2000)?
 */
val nonLeap = List(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
val leap = List(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

val init = 2 //1 Jan 1901 is Tuesday

def isLeap(y: Int): Boolean = {
  (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)
}

val months = for {
  year <- 1901 to 2000
  month <- if (isLeap(year)) leap else nonLeap
} yield month

months.init.foldLeft((2, 0)) { (tuple, month) =>
  val firstDayOfNextMonth = (tuple._1 + month) % 7
  (firstDayOfNextMonth, if (firstDayOfNextMonth == 0) tuple._2 + 1 else tuple._2)
}._2