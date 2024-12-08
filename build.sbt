lazy val root = project
  .in(file("."))
  .settings(
    name := "AdventOfCode",
    version := "1.0.0",
    scalaVersion := "3.6.1",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.19",
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
    )
  )
