package org.advent.utils

import play.api.libs.json._

import scala.concurrent.duration.*

object HelperClasses {

  case class PartLimiter(lastRun: Long = -1.seconds.fromNow.time.toNanos,
                         lastTimeUnit: String = -1.seconds.fromNow.time._2.toString.toLowerCase,
                         partSolved: Boolean = false)

  case class CombinedLimiter(part1: PartLimiter = PartLimiter(), part2: PartLimiter = PartLimiter())

  implicit val partLimiterFormat: Format[PartLimiter] = Json.format[PartLimiter]
  implicit val combinedLimiterFormat: Format[CombinedLimiter] = Json.format[CombinedLimiter]
}
