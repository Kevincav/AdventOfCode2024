package org.advent.io

import better.files.File
import cats.effect.IO
import play.api.libs.json.{Format, Json}

import scala.language.postfixOps
import scala.sys.process.*

private object Publisher {
  case class CombinedLimiter(part1Answer: Boolean = false, part2Answer: Boolean = false)
  implicit val combinedLimiterFormat: Format[CombinedLimiter] = Json.format[CombinedLimiter]

  case class LimitDetails(dayToPartSoledMap: Map[Int, CombinedLimiter] = Map.empty)
  implicit val limitDetailsFormat: Format[LimitDetails] = Json.format[LimitDetails]

  def getDurationDetailsFromFile(file: File): IO[LimitDetails] =
    IO { Json.fromJson[LimitDetails](Json.parse(if (file.isEmpty) "{}" else file.contentAsString)).getOrElse(LimitDetails()) }

  def writeDurationDetailsToFile[A](file: File, writeObject: LimitDetails): IO[Unit] =
    IO { file.writeText(Json.prettyPrint(Json.toJson(writeObject))) }
}

case class AoCPublisher(year: Int, day: Int)(file: String = s"src/main/resources/year$year") {
  private def pushToAoc(part: Int, answer: Long): IO[String] = IO {
    List("curl", "-X", "POST", "--cookie", s"'session=${sys.env("AOC_COOKIE_SESSION")}'", "-H",
      s"'User-Agent: ${sys.env("AOC_COOKIE_SESSION")}'", "--data", s"'level=$part&answer=$answer'",
      s"https://adventofcode.com/$year/day/$day/answer").mkString(" ") !!
  }

  private def checkIfSolved(page: String) =
    page.contains("That's the right answer") || page.contains("Did you already complete it")

  private def pushAnswer(answer: Long, part: Int): IO[Boolean] = for {
    aocPushResult <- pushToAoc(part, answer)
    _ <- IO { println(s"Submitting part $part for day $day, Answer for part $part: ${checkIfSolved(aocPushResult)}") }
  } yield checkIfSolved(aocPushResult)

  def publishAnswer(part1: Long, part2: Long): IO[Unit] =
    if (!sys.env("AOC_SUBMIT_ANSWERS").toBoolean) IO {} else for {
      newFile <- IO { File(s"$file/answers").createIfNotExists(createParents = true) }
      limiter <- RateLimiter(file).checkIfAvailableAndUpdate(s"Day $day")
      answerDetailMap <- Publisher.getDurationDetailsFromFile(newFile)
      dayAnswerDetails <- IO { answerDetailMap.dayToPartSoledMap.getOrElse(day, Publisher.CombinedLimiter()) }
      part1Result <- if (!(dayAnswerDetails.part1Answer && limiter)) pushAnswer(part1, 1) else IO { true }
      part2Result <- if (!(dayAnswerDetails.part1Answer && limiter)) pushAnswer(part1, 1) else IO { true }
      response <- IO { Publisher.LimitDetails(answerDetailMap.dayToPartSoledMap
        .updated(day, Publisher.CombinedLimiter(part1Result, part2Result))) }
      _ <- Publisher.writeDurationDetailsToFile(newFile, response)
    } yield ()
}
