import Dependencies._

ThisBuild / scalaVersion     := "3.1.2"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "eu.brosbit"
ThisBuild / organizationName := "brosbit"


lazy val root = (project in file(".")).
  aggregate(aCross.js, aCross.jvm).
  settings(
    crossScalaVersions ++= Seq("2.13.6", "3.1.2")
  )
  
 lazy val aCross = crossProject(JSPlatform, JVMPlatform).in(file(".")).
   settings(
     name := "hexlib"
   ).
   jvmSettings(
    libraryDependencies ++= Seq(
	 "org.scalatest" %% "scalatest" % "3.2.10" % "test",
	 "org.scala-js" %% "scalajs-stubs" % "1.1.0" % "provided"
    )
   ).
   jsSettings(
    scalaJSUseMainModuleInitializer := true
   )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype. .enablePlugins(ScalaJSPlugin)
