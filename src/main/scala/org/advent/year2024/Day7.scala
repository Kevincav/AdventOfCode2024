package org.advent.year2024

import org.advent.utils.Problem

import scala.annotation.targetName

object Day7 extends Problem[List[(Long, List[Long])]](2024, 7) {
  extension (a: Long)
    @targetName("concat")
    inline def ||(b: Long): Long = (a * math.pow(10, math.log10(math.abs(b)).toInt + 1)).toLong + b

  private def checkCalculation(values: List[Long], total: Long, result: Long, concat: Boolean = false): Boolean =
    values match {
      case Nil => result == total
      case x :: xs => checkCalculation(xs, total, result + x, concat) || checkCalculation(xs, total, result * x, concat)
        || (if (concat) checkCalculation(xs, total, result || x, concat) else false)
    }

  override def setup(list: List[String]): List[(Long, List[Long])] =
    list.map { case s"$total: $values" => total.toLong -> values.split(' ').map(_.toLong).toList }

  override def solution1(input: List[(Long, List[Long])]): Long =
    input.withFilter((total, values) => checkCalculation(values.tail, total, values.head)).map(_._1).sum

  override def solution2(input: List[(Long, List[Long])]): Long =
    input.withFilter((total, values) => checkCalculation(values.tail, total, values.head, true)).map(_._1).sum
}
