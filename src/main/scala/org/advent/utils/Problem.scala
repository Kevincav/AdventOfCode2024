package org.advent.utils

import java.time.LocalDate
import scala.Console.CYAN
import scala.io.Source

case class Result[A](name: String, result: A, elapsedTime: Time, date: LocalDate) {
  override def toString = s"${CYAN}Advent of Code $date:\n — Operation: $name\n — Duration: $elapsedTime\n — Result: $result\n"
}

abstract class Problem[A](year: Int, day: Int) {
  private def fetchData: List[String] = Source.fromResource(s"year$year/input/Day$day.input").getLines().toList

  def setup(input: List[String]): A

  def solution1(input: A): Any

  def solution2(input: A): Any

  def run(): Unit = {
    val (fetchResult, fetchTime) = Timer { fetchData }
    println(Result("Fetch Data", s"${fetchResult.size} lines of data", fetchTime, LocalDate.of(year, 12, day)))

    val (setupResult, setupTime) = Timer { setup(fetchResult) }
    println(Result("Setup Data", s"${fetchResult.size} lines of setup data", setupTime, LocalDate.of(year, 12, day)))

    val (part1Result, part1Time) = Timer { solution1(setupResult) }
    println(Result("Part 1 Solution", part1Result, part1Time, LocalDate.of(year, 12, day)))

    val (part2Result, part2Time) = Timer { solution2(setupResult) }
    println(Result("Part 2 Solution", part2Result, part2Time, LocalDate.of(year, 12, day)))
  }
}
