name := "Euler"

version := "1.0"

scalaVersion := "2.11.1"

libraryDependencies +=  "org.gabbi" % "mycommons_2.11" % "1.0"

addCommandAlias("generate-project", ";update-classifiers;gen-idea")