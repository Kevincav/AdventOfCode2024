package org.advent.year2024

import org.advent.utils.Problem

import scala.annotation.{tailrec, targetName}

object Day9 extends Problem[List[Option[Int]]](2024, 9) {
  extension[A] (a: Array[A])
    @targetName("swap")
    private inline def swap(i: Int, j: Int): Unit = { val temp = a(i); a(i) = a(j); a(j) = temp }

  @tailrec
  private def reverseList(list: Array[Option[Int]])(left: Int = 0, right: Int = list.length - 1): Array[Int] =
    (left, right) match {
      case (i, j) if i > j => list.flatten
      case (i, j) if list(i).isDefined => reverseList(list)(i + 1, j)
      case (i, j) if list(j).isEmpty => reverseList(list)(i, j - 1)
      case (i, j) => list.swap(i, j); reverseList(list)(i + 1, j - 1)
    }

  private def getIndices(data: List[Option[Int]])(f: Option[Int] => Boolean)
                        : List[(Int, Option[Int], Int)] =
    data.zipWithIndex.filter(elem => f(elem._1)).scanLeft((-1, Option[Int](-1), 1)) {
      case ((sum, curr, i), (next, j)) => (sum + (if (i + 1 == j && curr == next) 0 else 1), next, j) }.drop(1)

  private def reduceCounts(data: List[(Int, Option[Int], Int)], order: Int = 1)
                          : List[(Int, Int, Option[Int])] = {
    data.groupMap(_._1)(elem => (elem._2, elem._3))
      .map((count, list) => (list.minBy(_._2)._2, list.size, list.head._1)).toList.sortBy(order * _._1)
  }

  @tailrec
  private def mergeLists(ranges: List[(Int, Int, Option[Int])], zeroes: List[(Int, Int, Option[Int])],
                         results: List[(Int, Int, Option[Int])] = Nil): List[(Int, Int, Option[Int])] =
    ranges match {
      case Nil => results
      case x :: xs => zeroes.zipWithIndex.collectFirst { case elem if elem._1._2 >= x._2 => elem } match {
        case None => mergeLists(xs, zeroes, results :+ x)
        case Some(elem, i) if elem._1 >= x._1 => mergeLists(xs, zeroes, results :+ x)
        case Some(elem, i) if x._2 == elem._2 => mergeLists(xs, zeroes.filter(_ != elem), results :+ x.copy(_1 = elem._1))
        case Some(elem, i) => mergeLists(xs, zeroes.updated(i, (zeroes(i)._1 + x._2, zeroes(i)._2 - x._2, zeroes(i)._3)),
          results :+ x.copy(_1 = elem._1))
      }
    }

  override def setup(input: List[String]): List[Option[Int]] =
    val (left, right) = (input.head.map(_.asDigit) ++ (if (input.size % 2 == 0) Nil else List(0))).zipWithIndex.partition(_._2 % 2 == 0)
    left.zip(right).flatMap { case ((l, i), (r, j)) => List.fill(l)(Some(i / 2)) ::: List.fill(r)(None) }.toList

  override def solution1(data: List[Option[Int]]): Long =
    reverseList(data.toArray)().zipWithIndex.foldLeft(0L){ case (sum, (i, j)) => sum + i * j }

  override def solution2(data: List[Option[Int]]): Long =
    val nones = reduceCounts(getIndices(data)(_.isEmpty))
    val ranges = reduceCounts(getIndices(data)(_.isDefined), -1)
    mergeLists(ranges, nones).flatMap((i, range, value) => (i until i + range).map(_.toLong * value.getOrElse(0))).sum
}
