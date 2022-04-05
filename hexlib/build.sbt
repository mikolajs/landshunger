val scala3Version = "3.1.0"


cancelable in Global := true

fork / run := true

lazy val root = project.
  in(file("."))
  .settings(
    organization    := "eu.brosbit",
    scalaVersion    := scala3Version,
    version := "0.1.0" ,
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.10",
      "org.scalatest" %% "scalatest" % "3.2.10" % "test",
      "ch.qos.logback"    % "logback-classic"           % "1.2.3"
    )
  )

