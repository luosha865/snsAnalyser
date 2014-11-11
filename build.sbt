import AssemblyKeys._

assemblySettings

name := "snsAnalyser"

version := "1.0"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

scalaVersion := "2.10.3"

libraryDependencies += "us.codecraft" % "webmagic-core" % "0.5.2"

libraryDependencies += "us.codecraft" % "webmagic-extension" % "0.5.2"

libraryDependencies += "us.codecraft" % "webmagic-selenium" % "0.5.2"

libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.0"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.2.1"

libraryDependencies += "org.neo4j" % "neo4j-kernel" % "2.1.5"

