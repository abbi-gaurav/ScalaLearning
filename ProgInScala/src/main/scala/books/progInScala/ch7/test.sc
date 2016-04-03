import java.io.File

def greet() = { println("hi") }

greet() == ()

val filesHere = new File("/Users/gabbi/learning/coursera/scala/progFunWS/ProgInScala/src/main/scala/books.progInScala.ch7").listFiles

def fileLines(file:File) = scala.io.Source.fromFile(file).getLines.toList

def grep(pattern:String) = (for {
  file <- filesHere.toList
  fileName: String = file.getName
  if fileName.endsWith("sc")
  line <- fileLines(file)
  trimmed = line.trim
  if trimmed.matches(pattern)
} yield fileName + " : " + trimmed) mkString "\n"

grep(".*file.*")

def makeRowSeq(row:Int) = {
  for{
    col <- 1 to 10
  }yield {
    val prod = (row * col).toString
    val padding = " " * (4 - prod.length)
    padding+prod
  }
}

def makeRow(row:Int) = makeRowSeq(row).mkString

def multiTable = (for (row <- 1 to 10) yield makeRow(row)) mkString "\n"

multiTable