package Solutions

import utils.Problem

import scala.annotation.tailrec

object Day3 extends Problem[List[String]](2024, 3) {
  private val mul = """mul\((\d{1,3}),(\d{1,3})\)""".r
  private val regex = """mul\([0-9]{1,3},[0-9]{1,3}\)|do\(\)|don't\(\)""".r

  @tailrec
  private def getMulSum(row: List[String], enabled: Boolean = true, value: Int = 0)(implicit skipDo: Boolean = true): Int =
    row match {
      case Nil => value
      case "do()" :: xs if !skipDo => getMulSum(xs, true, value)
      case "don't()" :: xs if !skipDo => getMulSum(xs, false, value)
      case mul(left, right) :: xs if enabled || skipDo => getMulSum(xs, enabled, value + left.toInt * right.toInt)
      case _ :: xs => getMulSum(xs, enabled, value)
    }

  override def parse(input: List[String]): List[String] = input.flatMap(row => regex.findAllIn(row).toList)

  override def solution1(list: List[String]): Int = getMulSum(list)

  override def solution2(list: List[String]): Int = getMulSum(list)(false)
}