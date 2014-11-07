import AssemblyKeys._

assemblySettings

name := "snsAnalyser"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies += "org.apache.nutch" % "nutch" % "2.2.1"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.2.1"