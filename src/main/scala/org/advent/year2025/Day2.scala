package org.advent.year2025

import org.advent.utils.Problem

import scala.collection.immutable.NumericRange

object Day2 extends Problem[Array[NumericRange.Inclusive[Long]]](2025, 2) {
  private def findDivisors(value: Long)(length: Int = value.toString.length): List[Int] =
    (1 to length).filter(x => length % x == 0 && length / x >= 2).toList

  private def isValid(value: Long, size: Int): Boolean = {
    val subStrings = value.toString.sliding(size, size).toList
    subStrings.tail.forall(_ == subStrings.head)
  }

  override def setup(input: List[String]): Array[NumericRange.Inclusive[Long]] =
    input.head.split(",").map(range => { val split = range.split("-"); split.head.toLong to split.last.toLong })

  override def solution1(data: Array[NumericRange.Inclusive[Long]]): Long =
    data.map(_.filter(value => value.toString.length % 2 == 0 && isValid(value, value.toString.length / 2)).sum).sum

  override def solution2(data: Array[NumericRange.Inclusive[Long]]): Long =
    data.map(_.filter(value => findDivisors(value)().exists(i => isValid(value, i))).sum).sum
}
