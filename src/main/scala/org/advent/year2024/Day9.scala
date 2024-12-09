package org.advent.year2024

import org.advent.utils.Problem

import scala.annotation.{tailrec, targetName}
import scala.collection.immutable.SortedMap

object Day9 extends Problem[List[Option[Int]]](2024, 9) {
  extension (a: Range)
    @targetName("intersect")
    inline def <>(b: Range): Range = a.end + 1 until b.start
    @targetName("sub")
    inline def -(b: Int): Range = a.start to a.end - b
    @targetName("add")
    inline def +(b: Int): Range = a.start to a.end + b

  @tailrec
  private def reverseList(array: Array[Option[Int]])(left: Int = 0, right: Int = array.length - 1): Array[Int] =
    (left, right) match {
      case (i, j) if i > j => array.flatten
      case (i, j) if array(i).isDefined => reverseList(array)(i + 1, j)
      case (i, j) if array(j).isEmpty => reverseList(array)(i, j - 1)
      case (i, j) => val temp = array(i); array(i) = array(j); array(j) = temp; reverseList(array)(i + 1, j - 1)
    }

  @tailrec
  private def moveWholeFiles(array: Array[Option[Int]], ranges: SortedMap[Int, Range], nones: List[Range]): Array[Option[Int]] =
    ranges match {
      case map if map.isEmpty => array
      case map =>
        val (value, range) = map.last
        nones.zipWithIndex.collectFirst { case current if current._1.size >= range.size => current } match {
          case None => moveWholeFiles(array, ranges - value, nones)
          case Some((current, i)) => current.dropRight(current.size - range.size).foreach(array(_) = Some(value))
            range.foreach(array(_) = None)
            moveWholeFiles(array, ranges - value, nones.updated(i, nones(i).drop(range.size)))
        }
    }

  @tailrec
  private def getNoneRanges(list: List[Int], range: Option[Range] = None, result: List[Range] = Nil): List[Range] =
    (list, range) match {
      case (Nil, None) => result
      case (Nil, Some(range)) => result :+ range
      case (x :: xs, None) => getNoneRanges(xs, Some(x to x), result)
      case (x :: xs, Some(range)) if x != range.last + 1 => getNoneRanges(xs, Some(x to x), result :+ range)
      case (_ :: xs, Some(range)) => getNoneRanges(xs, Some(range + 1), result)
    }

  @tailrec
  private def getRanges(list: List[(Int, Int)], value: Option[Int] = None, range: Option[Range] = None,
                        result: SortedMap[Int, Range] = SortedMap.empty): SortedMap[Int, Range] =
    (list, value, range) match {
      case (Nil, _, None) => result
      case (Nil, Some(x), Some(range)) => result + (x -> range)
      case ((x, i) :: xs, None, _) => getRanges(xs, Some(x), Some(i to i), result)
      case ((x, i) :: xs, Some(y), Some(range)) if x != y || i != range.last + 1 => getRanges(xs, Some(x), Some(i to i), result + (y -> range))
      case ((x, i) :: xs, Some(y), Some(range)) => getRanges(xs, value, Some(range + 1), result)
      case _ => result
    }

  override def setup(input: List[String]): List[Option[Int]] = {
    val (left, right) = (input.head.map(_.toInt - 48) ++ (if (input.size % 2 == 0) Nil else List(0))).zipWithIndex.partition(_._2 % 2 == 0)
    left.zip(right).flatMap { case ((l, i), (r, j)) => List.fill(l)(Some(i / 2)) ::: List.fill(r)(None) }.toList
  }

  override def solution1(data: List[Option[Int]]): Long =
    reverseList(data.toArray)().zipWithIndex.foldLeft(0L){ case (sum, (i, j)) => sum + i * j }

  override def solution2(data: List[Option[Int]]): Long =
    moveWholeFiles(data.toArray, getRanges(data.zipWithIndex.filter(_._1.nonEmpty).flatMap((i, j) => i.map(x => (x, j)))),
      getNoneRanges(data.zipWithIndex.withFilter(_._1.isEmpty).map(_._2)))
      .zipWithIndex.foldLeft(0L) { case (sum, (i, j)) => sum + i.getOrElse(0) * j }
}
