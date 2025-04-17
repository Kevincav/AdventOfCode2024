package org.advent.year2024

import org.advent.utils.Problem

object Day1 extends Problem[(List[Int], List[Int])](2024, 1) {
  override def setup(input: List[String]): (List[Int], List[Int]) = {
    val parsed = input.map(_.split(" ").withFilter(_.nonEmpty).map(_.toInt))
    (parsed.map(_.head), parsed.map(_.last))
  }

  override def solution1(data: (List[Int], List[Int])): Long =
    data._1.sorted.zip(data._2.sorted).map((x, y) => math.abs(x - y)).sum

  override def solution2(data: (List[Int], List[Int])): Long =
    data._1.map(i => i * data._2.count(j => i == j)).sum
}
