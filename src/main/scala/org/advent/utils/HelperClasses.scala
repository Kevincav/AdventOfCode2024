package org.advent.utils

import play.api.libs.json._

import scala.concurrent.duration.*

object HelperClasses {
  trait Part {
    def part: Int
  }
  case class Part1() extends Part { override val part: Int = 1 }
  case class Part2() extends Part { override val part: Int = 2 }

  case class PartLimiter(lastRun: Long = -1.seconds.fromNow.time.toNanos,
                         lastTimeUnit: String = -1.seconds.fromNow.time._2.toString.toLowerCase,
                         partSolved: Boolean = false)

  case class CombinedLimiter(part1: PartLimiter = PartLimiter(), part2: PartLimiter = PartLimiter())

  implicit val part1Format: Format[Part1] = Json.format[Part1]
  implicit val part2Format: Format[Part2] = Json.format[Part2]
  implicit val partLimiterFormat: Format[PartLimiter] = Json.format[PartLimiter]
  implicit val combinedLimiterFormat: Format[CombinedLimiter] = Json.format[CombinedLimiter]
}
