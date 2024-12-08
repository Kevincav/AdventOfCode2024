package org.advent.year2024

import org.advent.utils.Problem
import scala.annotation.tailrec

object Day3 extends Problem[List[String]](2024, 3) {
  private val mul = """mul\((\d{1,3}),(\d{1,3})\)""".r
  private val regex = """mul\([0-9]{1,3},[0-9]{1,3}\)|do\(\)|don't\(\)""".r

  @tailrec
  private def getMulSum(row: List[String], skipDo: Boolean = true, enabled: Boolean = true, value: Int = 0): Int =
    row match {
      case Nil => value
      case "do()" :: xs if !skipDo => getMulSum(xs, skipDo, true, value)
      case "don't()" :: xs if !skipDo => getMulSum(xs, skipDo, false, value)
      case mul(left, right) :: xs if enabled || skipDo => getMulSum(xs, skipDo, enabled, value + left.toInt * right.toInt)
      case _ :: xs => getMulSum(xs, skipDo, enabled, value)
    }

  override def setup(input: List[String]): List[String] = input.flatMap(row => regex.findAllIn(row).toList)

  override def solution1(list: List[String]): Int = getMulSum(list)

  override def solution2(list: List[String]): Int = getMulSum(list, false)
}
