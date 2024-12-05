package Solutions

import utils.Problem

object Day2 extends Problem[List[List[Int]]](2024, 2) {
  private def isOrdered(list: List[Int]): Boolean =
    list.sliding(2).count(row => 1 to 3 contains row.last - row.head) == list.size - 1

  private def checkRecords(list: List[List[Int]])(f: List[Int] => Boolean): Int =
    list.map(f).zip(list.map(row => f(row.reverse))).count((x, y) => x || y)

  override def parse(input: List[String]): List[List[Int]] =
    input.map(_.split(' ').toList.withFilter(_.nonEmpty).map(_.toInt))

  override def solution1(data: List[List[Int]]): Int = checkRecords(data)(isOrdered)

  override def solution2(data: List[List[Int]]): Int =
    checkRecords(data)(row => row.indices.exists(i => isOrdered(row.patch(i, Nil, 1))))
}
