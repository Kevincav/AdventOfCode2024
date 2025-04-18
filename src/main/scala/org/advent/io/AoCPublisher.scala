package org.advent.io

import cats.effect.IO
import rate.limiter.RateLimiter

import scala.language.postfixOps
import scala.sys.process.*

case class AoCPublisher(year: Int, day: Int)(file: String = s"src/main/resources/year$year") {
  private def pushToAoc(part: Int, answer: Any): IO[String] = IO {
    List("curl", "-X", "POST", "--cookie", s"'session=${sys.env("AOC_COOKIE_SESSION")}'", "-H",
      s"'User-Agent: ${sys.env("AOC_COOKIE_SESSION")}'", "--data", s"'level=$part&answer=$answer'",
      s"https://adventofcode.com/$year/day/$day/answer").mkString(" ") !!
  }

  private def checkIfSolved(page: String): Boolean =
    page.contains("That's the right answer") || page.contains("Did you already complete it")

  private def pushAnswer(answer: Any, part: Int): IO[Boolean] = for {
    aocPushResult <- pushToAoc(part, answer)
    _ <- IO { println(s"Submitting part $part for day $day, Answer ($answer) ${if (checkIfSolved(aocPushResult)) "is the correct" else "is the wrong"} solution") }
  } yield checkIfSolved(aocPushResult)

  def publishAnswer(part1: Any, part2: Any): IO[Unit] =
    if (!sys.env("AOC_SUBMIT_ANSWERS").toBoolean) IO {} else for {
      limiter <- IO { RateLimiter(year, day) }
      _ <- limiter.execute(part1, 1)(pushAnswer)
      _ <- limiter.execute(part2, 2)(pushAnswer)
    } yield ()
}
