//problem 4
/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.
 * @param high
 * @param low
 * @return
 */
def palindromes(high:Int, low:Int) = {
  for{
    x <- high to low by -1
    y <- x to low by -1
    prod = x*y
    if prod.toString == prod.toString.reverse
  }yield prod
}

import math.pow

def largestPalindromeProduct(numDigits:Int) = {
  palindromes(pow(10,numDigits).toInt-1, pow(10, numDigits - 1).toInt).max
}

largestPalindromeProduct(3)

//problem 5
/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 * @param n
 * @return
 */
def divisibleByAll(n:Int) = {
  (n to Int.MaxValue).find(x => (2 to n).forall( x % _ == 0)).get
}
divisibleByAll(20)

//problem 6
/**
 * The sum of the squares of the first ten natural numbers is,

12 + 22 + ... + 102 = 385
The square of the sum of the first ten natural numbers is,

(1 + 2 + ... + 10)2 = 552 = 3025
Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 * @param n
 * @return
 */
def squareOfSumMinusSumOfSquares(n:Int) = {
  val r = 1 to n
  val sum: Int = r.sum
  val sqrOfSum = sum * sum
  val sumOfSquares = r.map(x=>x*x).sum
  sqrOfSum - sumOfSquares
}

squareOfSumMinusSumOfSquares(100)

