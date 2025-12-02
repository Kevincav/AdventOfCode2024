package org.advent.year2025

import org.advent.utils.Problem

import scala.collection.immutable.NumericRange

object Day2 extends Problem[Array[NumericRange.Inclusive[Long]]](2025, 2) {
  private def isValid(value: Long): Boolean = {
    def countSubString(string: String, substring: String): Int =
      string.sliding(substring.length, substring.length).count(_ == substring)

    val subStrings = value.toString.splitAt(value.toString.length / 2)._1.scanLeft("")(_ + _).tail
    subStrings.collectFirst { case subString if countSubString(value.toString, subString) * subString.length == value.toString.length => subString }.isDefined
  }

  override def setup(input: List[String]): Array[NumericRange.Inclusive[Long]] =
    input.head.split(",").map(range => { val split = range.split("-"); split.head.toLong to split.last.toLong })

  override def solution1(data: Array[NumericRange.Inclusive[Long]]): Long = {
    def isValid(value: BigInt): Boolean = if (value.toString.length % 2 == 1) false else {
      val split = value.toString.splitAt(value.toString.length / 2)
      split.head == split.last
    }

    data.map(_.filter(isValid).sum).sum
  }

  override def solution2(data: Array[NumericRange.Inclusive[Long]]): Long = data.map(_.filter(isValid).sum).sum
}
