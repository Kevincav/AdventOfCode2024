package org.advent.year2025

import org.advent.utils.Problem

import cats.collections.Range
import scala.annotation.tailrec

object Day5 extends Problem[(List[Range[Long]], List[Long])](2025, 5) {
  override def setup(input: List[String]): (List[Range[Long]], List[Long]) = {
    @tailrec
    def mergeRanges(list: List[Range[Long]], result: List[Range[Long]] = List.empty): List[Range[Long]] =
      list match {
        case Nil => result.reverse
        case x :: Nil => (x :: result).reverse
        case Range(x1, x2) :: Range(y1, y2) :: xs if x2 >= y1 => mergeRanges(Range(x1, math.max(x2, y2)) :: xs, result)
        case x :: y :: xs => mergeRanges(y :: xs, x :: result)
      }

    val (ranges, ids) = input.splitAt(input.zipWithIndex.find(_._1.isEmpty).get._2)
    (mergeRanges(ranges.map { case s"$start-$end" => Range(start.toLong, end.toLong) }.sortBy(_._1)), ids.drop(1).map(_.toLong))
  }

  override def solution1(data: (List[Range[Long]], List[Long])): Long = data._2.count(id => data._1.exists(_.contains(id)))

  override def solution2(data: (List[Range[Long]], List[Long])): Long =
    data._1.foldLeft(0L) { case (sum, range) => sum + (range.end - range.start) + 1 }
}
