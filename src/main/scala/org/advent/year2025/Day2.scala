package org.advent.year2025

import org.advent.utils.Problem

import scala.collection.immutable.NumericRange

object Day2 extends Problem[Array[NumericRange.Inclusive[Long]]](2025, 2) {
  private def findDivisors(len: Int): List[Int] = (1 to len).filter(d => len % d == 0 && len / d >= 2).toList

  private def isValidDigits(digits: String, size: Int): Boolean = {
    val chunk = digits.take(size)
    digits.grouped(size).forall(_ == chunk)
  }

  override def setup(input: List[String]): Array[NumericRange.Inclusive[Long]] = input.head.split(',').map { range =>
    val Array(low, high) = range.split('-')
    low.toLong to high.toLong
  }

  override def solution1(data: Array[NumericRange.Inclusive[Long]]): Long =
    data.map(_.filter { value => value.toString.length % 2 == 0 && isValidDigits(value.toString, value.toString.length / 2) }.sum).sum

  override def solution2(data: Array[NumericRange.Inclusive[Long]]): Long =
    data.map(_.filter { value => findDivisors(value.toString.length).exists(size => isValidDigits(value.toString, size)) }.sum).sum
}