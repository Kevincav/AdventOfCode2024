package org.advent.year2024

import org.advent.utils.{Graph, Position, Problem, graphDirections}

import scala.annotation.tailrec

object Day12 extends Problem[Graph](2024, 12) {
  private def dfs(graph: Graph, position: Position, key: Char, set: Set[Position] = Set.empty): Set[Position] =
    if (!graph.checkBounds(position) || graph(position) != key || set.contains(position)) set
    else graphDirections.foldLeft(set){ case (curr, next) => dfs(graph, position + next, key, curr + position) }

  @tailrec
  private def runDfs(graph: Graph, positions: List[Position], result: Map[Position, Set[Position]] = Map.empty): Map[Position, Set[Position]] =
    positions match {
      case Nil => result
      case x :: xs if result.exists(_._2.contains(x)) => runDfs(graph, xs, result)
      case x :: xs => runDfs(graph, xs, result + (x ->  dfs(graph, x, graph(x))))
    }

  private def checkSides(graph: Graph, positions: List[Position], key: Char, result: Int = 0): Int = (positions.size * 4 -
    positions.map(curr => graphDirections.count(pos => graph.checkBounds(curr + pos) && graph(curr + pos) == key)).sum) * positions.size

  override def setup(input: List[String]): Graph = Graph(input)

  override def solution1(graph: Graph): Long = {
    val positions = graph().indices.flatMap(i => graph(i).indices.map(j => Position(i, j))).toList
    runDfs(graph, positions).map((pos, set) => checkSides(graph, set.toList, graph(pos))).sum
  }

  override def solution2(graph: Graph): Long = 0
}
