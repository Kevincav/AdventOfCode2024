package org.advent.utils

import cats.effect.Clock
import cats.effect.IO

import java.time.LocalDate
import scala.Console.CYAN
import scala.io.Source

abstract class Problem[A](year: Int, day: Int) {
  private def fetchData: List[String] = Source.fromResource(s"year$year/input/Day$day.input").getLines().toList

  private def getOutputString[B](result: B, showResult: Boolean): String = if (showResult) result match {
    case list: List[_] => s"\nResult: ${list.size} lines of data processed"
    case result => s"\nResult: ${result.toString}"
  } else ""

  def setup(input: List[String]): A

  def solution1(input: A): Long

  def solution2(input: A): Long

  private def measure[B](name: String, showResult: Boolean = true)(lambda: IO[B]): IO[B] =
    Clock[IO].realTime.flatMap { startTime => lambda.flatMap { result => Clock[IO].realTime.map { endTime =>
      println(s"${CYAN}Advent of Code Date: ${LocalDate.of(year, 12, day)}\nOperation: $name\nTime Elapsed: " +
        s"${f"${(endTime - startTime).toMillis} milliseconds"}${getOutputString(result, showResult)}\n")
      result }}}

  def run(): IO[List[Any]] = for {
    fetchResult <- measure("Fetch Data")(IO { fetchData })
    setupResult <- measure("Setup Data", false)(IO { setup(fetchResult) })
    solution1Result <- measure("Run Solution 1")(IO { solution1(setupResult) })
    solution2Result <- measure("Run Solution 2")(IO { solution2(setupResult) })
    rateLimiter <- IO { RateLimiter(year, day) }
    _ <- rateLimiter.publishAnswer(solution1Result, solution2Result)
  } yield List(solution1Result, solution2Result)
}
