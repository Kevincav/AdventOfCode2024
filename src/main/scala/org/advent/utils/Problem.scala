package org.advent.utils

import sttp.client4.*

import java.time.LocalDate
import scala.Console.CYAN
import scala.io.Source

case class Result[A](name: String, result: A, elapsedTime: Double, date: LocalDate) {
  override def toString = s"${CYAN}Advent of Code $date:\n — Operation: $name\n — Duration: $elapsedTime seconds\n — Result: $result\n"
}

abstract class Problem[A](year: Int, day: Int) {
  private def fetchData: List[String] = Source.fromResource(s"year$year/input/Day$day.input").getLines().toList

  def setup(input: List[String]): A

  def solution1(input: A): Any

  def solution2(input: A): Any

  def run(): Unit = {
    val clockStart = System.nanoTime
    val data = fetchData
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
