/*
The following iterative sequence is defined for the set of positive integers:

n → n/2 (n is even)
n → 3n + 1 (n is odd)

Using the rule above and starting with 13, we generate the following sequence:

13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
Although it has not been proved yet (Collatz Problem), it is thought that all
starting numbers finish at 1.
Which starting number, under one million, produces the longest chain?

NOTE: Once the chain starts the terms are allowed to go above one million.
 */

def collatzLength(n: Int): Int = {
  //having long is the key,since 3*n+1 can overshoot the Int Range
  def loop(current: Long, length: Int): Int = {
    if (current == 1) length
    else loop(if (current % 2 == 0) current / 2 else 3 * current + 1, length + 1)
  }
  loop(n, 1)
}
collatzLength(13)
(1 until 1000000) maxBy collatzLength
//foldLeft, reduceLeft can also be use
