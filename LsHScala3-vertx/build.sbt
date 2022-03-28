val scala3Version = "3.0.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-simple",
    version := "0.1.0",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "io.vertx" % "vertx-core" % "4.0.2",
      "io.vertx" % "vertx-web" % "4.0.2"
    )
  )
