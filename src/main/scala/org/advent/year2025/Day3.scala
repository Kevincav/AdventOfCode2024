package org.advent.year2025

import org.advent.utils.Problem

object Day3 extends Problem[List[String]](2025, 3) {
  override def setup(input: List[String]): List[String] = input

  private def getMaxRow(row: String, size: Int): Long = row.indices.combinations(size).map(_.map(row)).max.mkString.toLong

  override def solution1(data: List[String]): Long = data.map(getMaxRow(_, 2)).sum

  override def solution2(data: List[String]): Long = data.map(getMaxRow(_, 12)).sum
}
