package books.progInScala.initial

import scala.io.Source

object PrintFormattedLines extends App {
	if (args.length > 0) {
		args.foreach(file => println(printFile(file)))

		def printFile(file: String): String = {
			def widthOfLength(line: String) = line.length.toString.length
			
			val lines = Source.fromFile(file).getLines().toList
			val maxWidth = widthOfLength(lines.maxBy(_.length))
			
			def formatLine(line:String) = {
				val numSpaces = maxWidth - widthOfLength(line)
				val padding = " " * numSpaces
				padding + line.length + " | " + line
			}
			(lines map formatLine).mkString("\n")
		}
	} else {
		println("please enter a file name")
	}
}