package org.advent.io

import better.files.File
import cats.effect.IO
import play.api.libs.json.{Format, Json}

import scala.concurrent.duration.*

private object Limiter {
  case class DurationDetails(time: Long = 0.minutes._1, timeUnit: String = 0.minutes._2.toString.toLowerCase) {
    def getDeadline: Deadline = FiniteDuration(time, timeUnit).fromNow
  }
  implicit val durationDetailsFormat: Format[DurationDetails] = Json.format[DurationDetails]

  case class KeyToDurationMap(keyToDurationMap: Map[String, DurationDetails] = Map.empty)
  implicit val keyToDurationMapFormat: Format[KeyToDurationMap] = Json.format[KeyToDurationMap]

  def getDurationDetailsFromFile(file: File): IO[KeyToDurationMap] =
    IO { Json.fromJson[Limiter.KeyToDurationMap](Json.parse(if (file.isEmpty) "{}" else file.contentAsString)).get }

  def writeDurationDetailsToFile(file: File, writeObject: KeyToDurationMap): IO[Unit] =
    IO { file.writeText(Json.prettyPrint(Json.toJson(writeObject))) }
}

case class RateLimiter(file: String) {
  def checkIfAvailableAndUpdate(key: String): IO[Boolean] = for {
    newFile <- IO { File(s"$file/limiters").createIfNotExists(createParents = true) }
    limiterDetails <- Limiter.getDurationDetailsFromFile(newFile)
    durationDetails <- IO { limiterDetails.keyToDurationMap.getOrElse(key, Limiter.DurationDetails()) }
    deadline <- IO { durationDetails.getDeadline }
    _ <- IO { if (deadline.hasTimeLeft()) println(s"Unable to update results. Time remaining: ${deadline.timeLeft.toMinutes} minute(s)")}
    newDeadline <- IO { if (deadline.isOverdue()) Limiter.DurationDetails(sys.env("AOC_SUBMISSION_THROTTLE").toInt.minutes._1,
      sys.env("AOC_SUBMISSION_THROTTLE").toInt.minutes._2.toString.toLowerCase) else durationDetails }
    response <- IO { Limiter.KeyToDurationMap(limiterDetails.keyToDurationMap.updated(key, newDeadline)) }
    _ <- Limiter.writeDurationDetailsToFile(newFile, response)
  } yield deadline.hasTimeLeft()
}
