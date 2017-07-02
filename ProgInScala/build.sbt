name := "ProgInScala"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.10",
  "org.scalatest" %% "scalatest" % "3.0.0-M14" % "test",
  "org.scala-lang.modules" %% "scala-xml" % "1.0.2",
  "org.scalacheck" %% "scalacheck" % "1.11.5" % "test",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",
  "commons-io" % "commons-io" % "2.4"
)