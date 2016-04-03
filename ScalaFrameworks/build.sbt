name := "ScalaFrameworks"

version := "1.0"

scalaVersion := "2.11.4"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.5"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.11"

libraryDependencies += "org.specs2" % "specs2_2.11" % "2.3.11"

libraryDependencies += "org.apache.openjpa" % "openjpa" % "2.3.0"

libraryDependencies +=  "org.gabbi" % "mycommons_2.11" % "1.0"

libraryDependencies += "com.netflix.rxjava" % "rxjava-core" % "0.20.7"

libraryDependencies += "com.netflix.rxjava" % "rxjava-scala" % "0.20.7"

libraryDependencies += "org.rogach" % "scallop_2.11" % "0.9.5"

libraryDependencies += "commons-io" % "commons-io" % "2.4"

libraryDependencies += "com.typesafe.akka" %% "akka-stream-experimental" % "1.0-M4"

addCommandAlias("generate-project", ";update-classifiers;update-sbt-classifiers;gen-idea sbt-classifiers")