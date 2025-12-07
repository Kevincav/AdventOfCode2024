package org.advent.year2025

import org.advent.utils.Problem

import cats.collections.Range
import scala.annotation.tailrec

object Day5 extends Problem[(List[Range[Long]], List[Long])](2025, 5) {
  override def setup(input: List[String]): (List[Range[Long]], List[Long]) = {
    val (ranges, ids) = input.splitAt(input.zipWithIndex.find(_._1.isEmpty).get._2)
    val newRanges = ranges.map { case s"$start-$end" => Range(start.toLong, end.toLong) }.sortBy(_._1)

    val mergedRanges = newRanges.tail.foldLeft(List(newRanges.head))((results, range) => results match {
      case x :: xs if range.start <= x.end => Range(x.start, math.max(range.end, x.end)) :: xs
      case _ => range :: results
    })

    (mergedRanges, ids.drop(1).map(_.toLong))
  }

  override def solution1(data: (List[Range[Long]], List[Long])): Long = data._2.count(id => data._1.exists(_.contains(id)))

  override def solution2(data: (List[Range[Long]], List[Long])): Long =
    data._1.foldLeft(0L) { case (sum, range) => sum + (range.end - range.start) + 1 }
}
