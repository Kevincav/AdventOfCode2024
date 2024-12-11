package org.advent.year2024

import org.advent.utils.Problem

import scala.collection.mutable

object Day11 extends Problem[List[Long]](2024, 11) {
  extension (a: Long)
    private inline def length: Int = math.log10(math.abs(a)).toInt + 1
    private inline def split: (Long, Long) = { val mul = math.pow(10, a.length / 2); ((a / mul).toLong, (a % mul).toLong) }

  private def blink(stone: Long, count: Int, map: mutable.Map[(Long, Long), Long] = mutable.Map.empty): Long = {
    map((stone, count)) =
      if (count == 0) 1
      else if (map.contains((stone, count))) map((stone, count))
      else if (stone == 0) blink(1, count - 1, map)
      else if (stone.length % 2 == 0) { val (left, right) = stone.split; blink(left, count - 1, map) + blink(right, count - 1, map) }
      else blink(stone * 2024, count - 1, map)
    map((stone, count))
  }

  override def setup(input: List[String]): List[Long] = List.from(input.head.split(" ")).map(_.toLong)

  override def solution1(stones: List[Long]): Long = stones.map(stone => blink(stone, 25)).sum

  override def solution2(stones: List[Long]): Long = stones.map(stone => blink(stone, 75)).sum
}