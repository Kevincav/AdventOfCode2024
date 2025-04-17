package org.advent.year2024

import org.advent.utils.Problem

object Day2 extends Problem[List[List[Int]]](2024, 2) {
  private def isSafe(list: List[Int]): Boolean = {
    def checkOrder(list: List[Int]): Boolean =
      list.zip(list.tail).count(xs => 1 to 3 contains xs._2 - xs._1) == list.size - 1
    checkOrder(list) || checkOrder(list.reverse)
  }

  override def setup(input: List[String]): List[List[Int]] = input.map(_.split(' ').map(_.toInt).toList)

  override def solution1(data: List[List[Int]]): Long = data.count(isSafe)

  override def solution2(data: List[List[Int]]): Long = data.count(xs => xs.indices.exists(i => isSafe(xs.patch(i, Nil, 1))))
}