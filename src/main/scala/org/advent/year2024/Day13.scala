package org.advent.year2024

import org.advent.utils.Problem

import scala.annotation.tailrec

object Day13 extends Problem[List[List[(Long, Long)]]](2024, 13) {
  @tailrec
  private def getPossibleIndexes(buttonA: (Long, Long), buttonB: (Long, Long), target: (Long, Long),
                                 result: List[(Long, Long)] = List.empty)(i: Long = 0L, j: Long = 0L): Option[Long] = {
    val newI = if (j + buttonB._2 <= target._1) i else i + buttonA._1
    val newJ = if (j + buttonB._2 <= target._1) j + buttonB._1 else 0L
    if (newI >= target._1) result.map(_+_).minOption
    else if (i + j == target._1 && i / buttonA._1 * buttonA._2 + j / buttonB._1 * buttonB._2 == target._2)
      getPossibleIndexes(buttonA, buttonB, target, result :+ (i / buttonA._1 * 3, j / buttonB._1 * 1))(newI, newJ)
    else getPossibleIndexes(buttonA, buttonB, target, result)(newI, newJ)
  }

  override def setup(input: List[String]): List[List[(Long, Long)]] =
    input.sliding(3, 4).map {
      case Seq(
        s"Button A: X+$ax, Y+$ay",
        s"Button B: X+$bx, Y+$by",
        s"Prize: X=$px, Y=$py",
      ) => List((ax.toLong, ay.toLong), (bx.toLong, by.toLong), (px.toLong, py.toLong))
      case _ => List.empty
    }.toList

  override def solution1(data: List[List[(Long, Long)]]): Long =
    data.flatMap(row => getPossibleIndexes(row.head, row(1), row.last)()).sum

  override def solution2(data: List[List[(Long, Long)]]): Long = data.map(row => row.updated(2, (row(2)._1 +
    0, row(1)._2 + 0))).flatMap(row => getPossibleIndexes(row.head, row(1), row.last)()).sum
}
