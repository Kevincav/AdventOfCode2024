package org.advent.utils

import sttp.client4.*

import java.time.LocalDate
import java.util.Date

case class Result[A](name: String, result: A, elapsedTime: Double, date: LocalDate) {
  override def toString = s"Advent of Code $date\nOperation: $name\nDuration: $elapsedTime seconds\nResult: $result\n"
}

abstract class Problem[A](year: Int, day: Int) {
  private def fetchData(sessionToken: String): List[String] =
    quickRequest
      .get(uri"https://adventofcode.com/$year/day/$day/input")
      .cookie("session", sessionToken)
      .header("User-Agent", "advent-of-code-data scala-v1").send(DefaultSyncBackend()).body.split("\n").toList

  def setup(input: List[String]): A

  def solution1(input: A): Any

  def solution2(input: A): Any

  def run(): Unit = {
    val clockStart = System.nanoTime
    val data = fetchData(sys.env("AOC_COOKIE_SESSION"))
    val fetchLap = System.nanoTime
    println(Result("Fetch Data", s"${data.size} lines of data", (fetchLap - clockStart) / 1e9d, LocalDate.of(year, 12, day)))

    val setupData = setup(data)
    val setupLap = System.nanoTime
    println(Result("Setup Data", s"${data.size} lines of setup data", (setupLap - fetchLap) / 1e9d, LocalDate.of(year, 12, day)))

    val result1 = solution1(setupData)
    val result1Lap = System.nanoTime
    println(Result("Part 1 Solution", result1, (result1Lap - setupLap) / 1e9d, LocalDate.of(year, 12, day)))

    val result2 = solution2(setupData)
    val result2Lap = System.nanoTime
    println(Result("Part 2 Solution", result2, (result2Lap - result1Lap) / 1e9d, LocalDate.of(year, 12, day)))
  }
}
