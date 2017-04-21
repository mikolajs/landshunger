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
  val akkaHTTPVer      = "10.0.5"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" % "akka-http_2.12"  % akkaHTTPVer,
    "com.typesafe.akka" % "akka-http-spray-json_2.12" % akkaHTTPVer,
    "com.typesafe.akka" % "akka-http-xml_2.12"  % akkaHTTPVer,
    "com.typesafe.play" %% "twirl-api" % "1.3.0",
    "org.scala-js" %% "scalajs-dom_sjs0.6" % "0.9.1",
    "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.2",
    "com.h2database" % "h2" % "1.4.191"
  )
}


