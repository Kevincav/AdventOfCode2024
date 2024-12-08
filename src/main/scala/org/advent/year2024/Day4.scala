package org.advent.year2024

import org.advent.libs.year2024.Day4.Input
import org.advent.utils.{Graph, Position, Problem, diagonalGraphDirections, graphDirections}

object Day4 extends Problem[Map[String, Input]](2024, 4) {
  private def dfs(paths: List[Position], target: List[Char], position: Position, direction: Option[Position] = None,
                  result: List[Position] = List.empty)(implicit graph: Graph): List[List[Position]] =
    (target, direction) match {
      case (Nil, _) => List(result)
      case (x :: _, _) if !graph.checkBounds(position) || graph(position) != x => Nil
      case (_ :: xs, Some(direction)) => dfs(paths, xs, position + direction, Some(direction), result :+ position)
      case (_ :: xs, _) => paths.flatMap(direction => dfs(paths, xs, direction + position, Some(direction), result :+ position))
    }

  private def getResultLists(positions: List[Position], graph: Graph, target: List[Char], paths: List[Position]): List[List[Position]] =
    positions.flatMap(position => dfs(paths, target, position)(graph))

  override def setup(input: List[String]): Map[String, Input] = {
    def getGraph: Graph = Graph(input.toArray.map(_.toCharArray))

    def getPositions(head: Char): List[Position] =
      input.zipWithIndex.flatMap((str, i) => str.zipWithIndex.withFilter(_._1 == head).map((_, j) => Position(i, j)))

    Map("XMAS" -> Input(getGraph, getPositions('X')), "MAS" -> Input(getGraph, getPositions('M')))
  }

  override def solution1(input: Map[String, Input]): Int =
    getResultLists(input("XMAS").positions, input("XMAS").graph, "XMAS".toList, diagonalGraphDirections).size

  override def solution2(input: Map[String, Input]): Int = {
    val left = getResultLists(input("MAS").positions, input("MAS").graph, "MAS".toList, graphDirections).toSet
    val right = left.map(mas => List(Position(mas.head()._1, mas.last()._2), mas(1), Position(mas.last()._1, mas.head()._2)))
    left.map(_.map(_())).map(_.sorted).intersect(right.map(_.map(_())).map(_.sorted)).size / 2
  }
}
