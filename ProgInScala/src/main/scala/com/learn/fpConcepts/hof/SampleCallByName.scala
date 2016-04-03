package com.learn.fpConcepts.hof

/**
 * Created by gabbi on 05/09/14.
 */
object SampleCallByName extends App{
  def func = {
    println("func")
    1
  }

  def func2(n:Int) = {
    println("func2")
    n*n
  }
  def hofByName[O](f : => O):O = {
    println("hofByName")
    val x = f
    x
  }

  def hofByValue[O](f : O):O = {
    println("hofByValue")
    val x = f
    x
  }

  hofByName(func)
  hofByName(func2(2))

  hofByValue(func)
  hofByValue(func2(2))
}
