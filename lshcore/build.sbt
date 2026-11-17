name := "LsHcore"

val scala3Version = "3.3.8"

ThisBuild / scalaVersion := "3.3.8"

//cancelable / Global := true

lazy val root = project.
  in(file("."))
  .settings(
    organization    := "eu.brosbit",
    scalaVersion    := scala3Version,
    version := "0.1.1" ,
    resolvers += Resolver.mavenLocal,
    libraryDependencies ++= Seq(
      "org.scalactic" % "scalactic_3" % "3.2.20",
      "org.scalatest" % "scalatest_3" % "3.2.20" % "test",
      "eu.brosbit" % "hexlib_3" % "0.2",
    )
  )
