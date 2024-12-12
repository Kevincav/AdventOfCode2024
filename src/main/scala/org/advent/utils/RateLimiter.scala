package org.advent.utils

import better.files.*
import cats.effect.IO
import org.advent.utils.HelperClasses.{CombinedLimiter, Part, Part1, Part2, PartLimiter}
import play.api.libs.json.*
import sttp.client4.*

import scala.concurrent.*
import scala.concurrent.duration.*
import scala.io.Source

case class RateLimiter(year: Int, day: Int) {
  private val file: File = File(s"src/main/resources/year$year/limiters")
  file.createIfNotExists(asDirectory = false, createParents = true)

  private def pushAnswer(answer: Long, part: Part): IO[PartLimiter] = {
    val result = quickRequest
      .post(uri"https://adventofcode.com/$year/day/$day/answer")
      .cookie("session", sys.env("AOC_COOKIE_SESSION"))
      .body(s"level=${part.part}&answer=$answer")
      .header("User-Agent", sys.env("AOC_USER_AGENT")).send(DefaultSyncBackend()).body

    IO {
      PartLimiter(
        sys.env("AOC_SUBMISSION_THROTTLE").toInt.minutes.fromNow.time.toNanos,
        sys.env("AOC_SUBMISSION_THROTTLE").toInt.minutes.fromNow.time._2.toString.toLowerCase,
        result.contains("That's the right answer") || result.contains("Did you already complete it")
      )
    }
  }

  def publishAnswer(result: Long, part: Part): IO[Unit] = {
    if (sys.env("AOC_SUBMIT_ANSWERS").toBoolean) {
      // Fetch last publish run data for the current part
      val dayLimiter: Map[Int, CombinedLimiter] =
        if (file.isEmpty) Map.empty.withDefaultValue(CombinedLimiter())
        else Json.fromJson[Map[Int, CombinedLimiter]](Json.parse(Source.fromResource(s"year$year/limiters").mkString))
          .get.withDefaultValue(CombinedLimiter())

      // If duration since last run >= limit && publish enabled submit
      val ableToRun = (if (part == Part1()) dayLimiter(day).part1 else dayLimiter(day).part2) match {
        case PartLimiter(_, _, true) => false
        case PartLimiter(lastRun, lastTimeUnit, _) => Deadline.apply(FiniteDuration.apply(lastRun, lastTimeUnit)).isOverdue()
      }

      // Check results for successful answer / submission and Save new limiter results
      pushAnswer(result, part).map(limiter => {
        val newLimiter: CombinedLimiter =
          if (ableToRun && part == Part1()) dayLimiter(day).copy(part1 = limiter)
          else if (ableToRun && part == Part2()) dayLimiter(day).copy(part2 = limiter)
          else dayLimiter(day)
        file.writeText(Json.prettyPrint(Json.toJson(dayLimiter.updated(day, newLimiter))))
      })
    } else IO {}
  }
}
