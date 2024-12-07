lazy val root = project
  .in(file("."))
  .settings(
    name := "AdventOfCode2024",
    version := "1.0.0",
    scalaVersion := "3.4.3",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M19",
    )
  )
