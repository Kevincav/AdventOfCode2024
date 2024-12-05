lazy val root = project
  .in(file("."))
  .settings(
    name := "AdventOfCode2023",
    version := "0.1.0",
    scalaVersion := "3.4.3",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.7",
      "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M19",
    )
  )
