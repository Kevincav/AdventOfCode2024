package org.advent.year2025

import org.advent.utils.Problem

object Day1 extends Problem[List[Int]](2025, 1) {
  override def setup(input: List[String]): List[Int] = input.map(value => (if (value.head == 'L') -1 else 1) * value.tail.toInt)

  override def solution1(data: List[Int]): Long = data.foldLeft((50, 0)) {
    case ((dial, count), turn) => ((dial + turn) % 100, count + (if ((dial + turn) % 100 == 0) 1 else 0)) }._2

  override def solution2(data: List[Int]): Long = data.foldLeft((50, 0L)) { case ((dial, count), turn) => (Math.floorMod(dial + turn, 100),
    count + turn.abs / 100 + (if (turn > 0 && dial + (turn % 100) >= 100 || turn < 0 && dial > 0 && dial + (turn % 100) <= 0) 1 else 0)) }._2
}
