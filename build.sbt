lazy val root = project
  .in(file("."))
  .settings(
    name := "AdventOfCode",
    version := "1.0.0",
    scalaVersion := "3.6.1",
    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.19",
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.typelevel" %% "cats-effect" % "3.5.7",
      "com.github.pathikrit" %% "better-files" % "3.9.2",
      "com.typesafe.play" %% "play-json" % "2.10.6",
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.1.0",
      "com.github.Kevincav" %% "aoc-rate-limiter" % "v1.0.0",
    ))
