package org.advent.year2025

import org.advent.utils.Problem

object Day6 extends Problem[(List[List[String]], List[Char])](2025, 6) {
  private def parseColumn(list: List[String]): List[Long] = list.head.indices.reverse.map(j =>
    list.indices.foldLeft(0L)((sum, i) => if (list(i)(j) == ' ') sum else sum * 10 + list(i)(j).asDigit)).toList

  private def calculate(list: List[List[Long]], operators: List[Char]) =
    operators.indices.map(i => if (operators(i) == '+') list(i).sum else list(i).product).sum

  override def setup(input: List[String]): (List[List[String]], List[Char]) = {
    def getSubstringRanges(input: List[String]): List[(Int, Int)] = {
      val subIndexes = input.map(_.toCharArray.zipWithIndex.withFilter(_._1 == ' ').map(_._2))
      val indexes = (0 +: subIndexes.reduce(_ intersect _).flatMap(i => List(i, i + 1)).sorted) :+ input.map(_.length).max
      indexes.sliding(2, 2).map(index => (index.head, index.last)).toList
    }

    def getSubStrings(rows: List[String]): List[List[String]] =
      rows.map(row => getSubstringRanges(rows).map((i, j) => row.substring(i, j))).transpose

    (getSubStrings(input.dropRight(1)), input.last.split(' ').withFilter(_.nonEmpty).map(_.head).toList)
  }

  override def solution1(data: (List[List[String]], List[Char])): Long = calculate(data._1.map(_.map(_.strip().toLong)), data._2)

  override def solution2(data: (List[List[String]], List[Char])): Long = calculate(data._1.map(parseColumn), data._2)
}
