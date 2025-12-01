package org.advent.year2024

import org.advent.utils.Problem

object Day19 extends Problem[(List[String], List[String])](2024, 19) {
  override def setup(input: List[String]): (List[String], List[String]) =
    (input.head.split(", ").reverse.toList, input.drop(2))

  override def solution1(data: (List[String], List[String])): Long =
    data._2.count(pattern => data._1.foldLeft(pattern)((output, towel) => output.replaceAll(towel, "")).isEmpty)

  override def solution2(data: (List[String], List[String])): Long = 0
}
