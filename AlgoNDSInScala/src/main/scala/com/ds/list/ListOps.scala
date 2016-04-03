package com.ds.list

import com.ds.list.ListOps.CombinatorialList

import scala.util.Random

/**
 * Created by gabbi on 13/09/14.
 */
object ListOps {

  def foldOne[A](x: A, l: List[A]): List[List[A]] = {
    (1 to l.length).foldLeft(List(x :: l))((acc, i) => mixOne(i, x, l) :: acc)
  }

  def mixOne[A](i: Int, x: A, l: List[A]): List[A] = {
    l.slice(0, i) ::: (x :: l.slice(i, l.length))
  }

  def suffixes[A](list: List[A]): List[List[A]] = list match {
    case Nil => List(List())
    case _ => list :: suffixes(list.tail)
  }

  def slice[A](list: List[A])(from: Int, until: Int): List[A] = list.isEmpty || until == 0 match {
    case true => List()
    case _ => if (from == 0) list.head :: slice(list.tail)(from, until - 1) else slice(list.tail)(from - 1, until - 1)
  }

  val random = new Random

  def shuffle[A](xs: List[A]): List[A] = xs match {
    case Nil => List()
    case _ =>
      val i = random.nextInt(xs.size)
      val (fst, snd) = xs splitAt i
      xs(i) :: shuffle(if (snd.isEmpty) fst else fst ++ snd.tail)
  }

  implicit class CombinatorialList[A](list: List[A]) {
    val xSize = list.size

    def xCombinations(n: Int): List[List[A]] = {
      if (n > xSize) Nil
      else list match {
        case _ :: _ if n == 1 => list map (x => List(x))
        case hd :: tl => tl.xCombinations(n - 1).map(hd :: _) ::: tl.xCombinations(n)
      }
    }

    def xSubsets: List[List[A]] = (2 to xSize).foldLeft(list.xCombinations(1)) { case (acc, i) => list.xCombinations(i) ::: acc}

    def xVariations(n: Int): List[List[A]] = {
      def mixMany(x: A, tailVariations: List[List[A]]): List[List[A]] = tailVariations match {
        case hd :: tl => foldOne(x, hd) ::: mixMany(x, tl)
        case _ => Nil
      }
      if (n > xSize) Nil
      else list match {
        case _ :: _ if n == 1 => list.map(List(_))
        case hd :: tl => mixMany(hd, tl.xVariations(n - 1)) ::: tl.xVariations(n)
      }
    }

    def xPermutations: List[List[A]] = xVariations(xSize)
  }

}

object ForDebug extends App {
  (1 to 3).toList.xCombinations(2)
}

