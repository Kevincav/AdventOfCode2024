package Solutions

import utils.{Graph, Position, Problem, graphDirections, graphNonDiagonalDirections}

import scala.annotation.tailrec
import scala.collection.mutable

case class Input(graph: Graph, positions: List[Position])

object Day4 extends Problem[Map[String, Input]](2024, 4) {
  private def dfs(paths: List[Position], graph: Graph, target: List[Char], position: Position,
                  direction: Option[Position] = None, result: List[Position] = List.empty): List[List[Position]] =
    (target, direction) match {
      case (Nil, _) => List(result)
      case (x :: _, _) if !graph.checkBounds(position) || graph(position) != x => Nil
      case (_ :: xs, Some(direction)) => dfs(paths, graph, xs, position + direction, Some(direction), result :+ position)
      case (_ :: xs, _) => paths.flatMap(direction => dfs(paths, graph, xs, direction + position, Some(direction), result :+ position))
    }

  private def getResultLists(positions: List[Position], graph: Graph, target: List[Char], paths: List[Position]): List[List[Position]] =
    positions.flatMap(position => dfs(paths, graph, target, position))

  override def parse(input: List[String]): Map[String, Input] = {
    def getGraph: Graph = Graph(input.toArray.map(_.toCharArray))

    def getPositions(head: Char): List[Position] =
      input.zipWithIndex.flatMap((str, i) => str.zipWithIndex.withFilter(_._1 == head).map((_, j) => Position(i, j)))

    Map("XMAS" -> Input(getGraph, getPositions('X')), "MAS" -> Input(getGraph, getPositions('M')))
  }

  override def solution1(input: Map[String, Input]): Int =
    getResultLists(input("XMAS").positions, input("XMAS").graph, "XMAS".toList, graphDirections).size

  override def solution2(input: Map[String, Input]): Int = {
    val left = getResultLists(input("MAS").positions, input("MAS").graph, "MAS".toList, graphNonDiagonalDirections).toSet
    val right = left.map(mas => List(Position(mas.head()._1, mas.last()._2), mas(1), Position(mas.last()._1, mas.head()._2)))
    left.map(_.map(_())).map(_.sorted).intersect(right.map(_.map(_())).map(_.sorted)).size / 2
  }
}
