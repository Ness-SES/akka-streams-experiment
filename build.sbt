name := "SadServer"

version := "1.0"

scalaVersion := "2.11.1"

resolvers += "typesafe" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.3.4",
  "com.typesafe.play" %% "play-jdbc" % "2.3.4",
  "com.typesafe.play" %% "anorm" % "2.3.4",
  "com.typesafe.akka" %% "akka-http-core-experimental" % "0.9"
)

lazy val sadserver = project.in(file(".")).enablePlugins(PlayScala)