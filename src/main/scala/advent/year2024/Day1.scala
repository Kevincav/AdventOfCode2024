package advent.year2024

import advent.utils.Problem

object Day1 extends Problem[List[List[Int]]](2024, 1) {
  override def setup(input: List[String]): List[List[Int]] =
    input.map(_.split(' ').toList.withFilter(_.nonEmpty).map(_.toInt))

  override def solution1(data: List[List[Int]]): Int =
    data.map(_.head).sorted.zip(data.map(_.last).sorted).map((x, y) => scala.math.abs(x - y)).sum

  override def solution2(data: List[List[Int]]): Int = {
    val counts = data.groupMapReduce(_.last)(_ => 1)(_ + _)
    data.map(x => x.head * counts.getOrElse(x.head, 0)).sum
  }
}