package org.advent.year2025

import org.advent.utils.Problem

import scala.annotation.tailrec

object Day3 extends Problem[List[String]](2025, 3) {
  override def setup(input: List[String]): List[String] = input

  @tailrec
  private def getMaxNumber(string: String, count: Int, result: String = ""): Long = if (count <= 0) result.toLong else {
    val output = string.substring(0, string.length - count + 1).zipWithIndex.maxBy(_._1)
    getMaxNumber(string.substring(output._2 + 1), count - 1, result + output._1)
  }

  override def solution1(data: List[String]): Long = data.map(getMaxNumber(_, 2)).sum

  override def solution2(data: List[String]): Long = data.map(getMaxNumber(_, 12)).sum
}
