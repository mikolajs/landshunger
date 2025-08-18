val scala3Version = "3.6.3"

lazy val root = project
  .in(file("."))
  .settings(
    organization    := "eu.brosbit",
    name := "LsH-server-vertx",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "io.vertx" % "vertx-core" % "4.0.2",
      "io.vertx" % "vertx-web" % "4.0.2",
      "eu.brosbit" % "lshcore_3" % "0.1.1"
    )
  )
