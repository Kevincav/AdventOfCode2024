package org.advent.utils

import better.files.File
import cats.effect.IO
import org.advent.utils.HelperClasses.{CombinedLimiter, PartLimiter}
import play.api.libs.json.Json

import sys.process.*
import scala.concurrent.*
import scala.concurrent.duration.*
import scala.io.Source
import scala.language.postfixOps

case class RateLimiter(year: Int, day: Int) {
  private val file: File = File(s"src/main/resources/year$year/limiters")
  file.createIfNotExists(asDirectory = false, createParents = true)

  private def pushAnswer(answer: Long, part: Int): IO[PartLimiter] = {
    val result = List("curl", "-X", "POST", "--cookie", s"'session=${sys.env("AOC_COOKIE_SESSION")}'", "-H",
      s"'User-Agent: ${sys.env("AOC_COOKIE_SESSION")}'", "--data", s"'level=$part&answer=$answer'",
      s"https://adventofcode.com/$year/day/$day/answer").mkString(" ") !!

    IO {
      PartLimiter(
        sys.env("AOC_SUBMISSION_THROTTLE").toInt.minutes.fromNow.time.toNanos,
        sys.env("AOC_SUBMISSION_THROTTLE").toInt.minutes.fromNow.time._2.toString.toLowerCase,
        result.contains("That's the right answer") || result.contains("Did you already complete it")
      )
    }
  }

  def publishAnswer(part1: Long, part2: Long): IO[Unit] = {
    if (sys.env("AOC_SUBMIT_ANSWERS").toBoolean) {
      // Fetch last publish run data for the current part
      val dayLimiter: Map[Int, CombinedLimiter] =
        if (file.isEmpty) Map.empty.withDefaultValue(CombinedLimiter())
        else Json.fromJson[Map[Int, CombinedLimiter]](Json.parse(Source.fromResource(s"year$year/limiters").mkString))
          .get.withDefaultValue(CombinedLimiter())

      // If duration since last run >= limit && publish enabled submit
      val ableToRun = List(dayLimiter(day).part1, dayLimiter(day).part2).map {
        case PartLimiter(_, _, true) => false
        case PartLimiter(lastRun, lastTimeUnit, _) => Deadline.apply(FiniteDuration.apply(lastRun, lastTimeUnit)).isOverdue()
      }

      // Check results for successful answer / submission and Save new limiter results
      val newValues = pushAnswer(part1, 1).flatMap(part1Limiter => pushAnswer(part2, 2).map(part2Limiter =>
        dayLimiter.updated(day, dayLimiter(day).copy(part1 = part1Limiter, part2 = part2Limiter))))
      newValues.map(values => file.writeText(Json.prettyPrint(Json.toJson(values))))
    } else IO {}
  }
}
