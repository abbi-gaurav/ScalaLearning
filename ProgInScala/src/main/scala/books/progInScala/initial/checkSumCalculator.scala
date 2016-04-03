package books.progInScala.initial

import scala.collection.mutable.Map

object checkSumCalculator {
	private val cache = Map[String,Int]()
	
	def calculate(str:String):Int = {
		if(cache.contains(str)){
			cache(str)
		}else{
			val cksum = ~(str.map(_.toByte).sum & 0xFF) + 1
			cache += (str -> cksum)
			cksum
		}
	}
}