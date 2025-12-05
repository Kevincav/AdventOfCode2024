package org.advent.year2025

import org.advent.utils.Problem

import scala.annotation.tailrec

object Day5 extends Problem[(List[(Long, Long)], List[Long])](2025, 5) {
  override def setup(input: List[String]): (List[(Long, Long)], List[Long]) = {
    @tailrec
    def mergeRanges(list: List[(Long, Long)], result: List[(Long, Long)] = List.empty): List[(Long, Long)] =
      list match {
        case Nil => result.reverse
        case x :: Nil => (x :: result).reverse
        case x :: y :: xs if x._2 >= y._1 => mergeRanges((x._1, math.max(x._2, y._2)) :: xs, result)
        case x :: y :: xs => mergeRanges(y :: xs, x :: result)
      }

    val range = """(\d+)-(\d+)""".r
    val idx = """(\d+)""".r

    val (ranges, ids) = input.foldLeft((List.empty[(Long, Long)], List.empty[Long])) {
      case ((ranges, ids), row) => row match {
        case range(start, stop) => ((start.toLong, stop.toLong) :: ranges, ids)
        case idx(id) => (ranges, id.toLong :: ids)
        case _ => (ranges, ids)
      }
    }

    (mergeRanges(ranges.sortBy(_._1)), ids)
  }

  override def solution1(data: (List[(Long, Long)], List[Long])): Long =
    data._2.count(id => data._1.exists((from, to) => id >= from && id <= to))

  override def solution2(data: (List[(Long, Long)], List[Long])): Long =
    data._1.foldLeft(0L) { case (sum, (from, to)) => sum + (to - from) + 1 }
}
