package org.advent.utils

import java.time.LocalDate
import scala.Console.CYAN
import scala.concurrent.duration.Duration
import scala.io.Source
abstract class Problem[A](year: Int, day: Int) {
  private def fetchData: List[String] = Source.fromResource(s"year$year/input/Day$day.input").getLines().toList

  def setup(input: List[String]): A

  def solution1(input: A): Any

  def solution2(input: A): Any

  private def measure[B](name: String, showResults: Boolean = true)(codeBlock: => B): B = {
    val startTime = System.nanoTime()
    val result = codeBlock
    println(s"${CYAN}Advent of Code Date: ${LocalDate.of(year, 12, day)}\nOperation: $name\nTime Elapsed: " +
      s"${Duration.fromNanos(System.nanoTime() - startTime).toMillis} milliseconds\n${if (showResults)
        result match {
          case list: List[_] => s"Result: ${list.size} lines of data processed"
          case result => s"Result: ${result.toString}"
        } else ""}\n")

    result
  }

  def run(): List[Any] = {
    val fetchResult = measure("Fetch Data")(fetchData)
    val setupResult = measure("Setup Data", false)(setup(fetchResult))
    List(measure("Run Solution 1")(solution1(setupResult)), measure("Run Solution 2")(solution2(setupResult)))
  }
}
