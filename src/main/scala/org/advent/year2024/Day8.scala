package org.advent.year2024

import org.advent.utils.{Graph, Position, Problem}

import scala.annotation.tailrec

object Day8 extends Problem[Graph](2024, 8) {
  @tailrec
  private def findDistances(list: List[Position], range: Int = 1, result: Set[Position] = Set.empty): Set[Position] =
    list match {
      case Nil => result
      case x :: xs => findDistances(xs, range, result ++ xs.flatMap {
        case y if y > x => val distance = y - x; (1 to range).flatMap(i => List(y + distance * i, x - distance * i)).toSet
        case y => val distance = x - y; (1 to range).flatMap(i => List(y - distance * i, x + distance * i)).toSet
      }.toSet)
    }

  private def getMatrix(graph: Graph): Map[Char, List[Position]] = graph.graph.zipWithIndex.flatMap((row, i) =>
    row.zipWithIndex.withFilter(_._1 != '.').map((char, j) => (char, Position(i, j)))).toList.groupMap(_._1)(_._2)

  override def setup(input: List[String]): Graph = Graph(input.map(_.toCharArray).toArray)

  override def solution1(data: Graph): Int = getMatrix(data).values.flatMap(findDistances(_).toList).toSet.count(data.checkBounds)

  override def solution2(data: Graph): Int = (getMatrix(data).values.flatMap(row => findDistances(row, 50).toList).toList ++
      getMatrix(data).values.flatten).toSet.count(data.checkBounds)
}
