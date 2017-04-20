import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import play.twirl.sbt.SbtTwirl

name          := "landhungry"
organization  := "pl.edu.osp"
version       := "0.0.2"
scalaVersion  := "2.12.1"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

lazy val `landhungry` = (project in file(".")).enablePlugins(SbtTwirl, JavaAppPackaging)

libraryDependencies ++= {
  val akkaVersion      = "2.5.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaVersion,
    "com.typesafe.akka" %% "akka-http-xml-experimental" % akkaVersion,
    "com.typesafe.play" %% "twirl-api" % "1.2.1",
    "org.scala-lang.modules" % "scala-xml_2.21" % "1.0.2",
    "com.h2database" % "h2" % "1.4.191"
  )
}


