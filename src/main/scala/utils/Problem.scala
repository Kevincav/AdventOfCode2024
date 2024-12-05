package utils

import cats.effect.unsafe.implicits.global
import sttp.client4.*
import sttp.model.Uri
import utils.Timer

import java.nio.file.{Files, Paths}
import java.nio.file.StandardOpenOption.{CREATE, WRITE}
import scala.io.Source

abstract class Problem[A](year: Int, day: Int) {
  private def checkFolderExists(): Unit = if (Files.notExists(Paths.get(s"src/main/resources/$year/$day")))
    Files.createDirectories(Paths.get(s"src/main/resources/$year/$day"))

  private def getDaysInput: String = quickRequest
    .get(uri"https://adventofcode.com/$year/day/$day/input")
    .cookie("session", Source.fromResource("token").getLines().toList.head)
    .header("User-Agent", "advent-of-code-data scala-v1").send(DefaultSyncBackend()).body

  private def getTodayData(testRun: Boolean): List[String] = {
    val path = Paths.get(s"src/main/resources/$year/$day/solution")
    if (Files.notExists(path)) Files.writeString(path, getDaysInput, CREATE, WRITE)
    Source.fromResource(s"$year/$day/${if (testRun) "example" else "solution"}").getLines().toList
  }

  def parse(input: List[String]): A

  def solution1(input: A): Int

  def solution2(input: A): Int

  def run(testRun: Boolean = false): Unit = {
    checkFolderExists()
    val solutionInput = getTodayData(testRun)
    val solution = parse(solutionInput)
    println(Timer { solution1(solution) })
    println(Timer { solution2(solution) })
  }
}
