package org.advent.year2024

import org.advent.utils.{Graph, Position, Problem, diagonalGraphDirections, onlyDiagonalDirections}

object Day4 extends Problem[Graph](2024, 4) {
  private def dfs(graph: Graph, position: Position, target: List[Char], path: Option[Position] = None, result: List[Position] = Nil)
                 (directions: List[Position] = diagonalGraphDirections): List[List[Position]] =
    (target, path) match {
      case (Nil, _) => List(result)
      case (x :: xs, _) if !graph.checkBounds(position) || graph(position) != x => Nil
      case (x :: xs, Some(path)) => dfs(graph, position + path, xs, Some(path), result :+ position)()
      case (x :: xs, None) => directions.flatMap(path => dfs(graph, position + path, xs, Some(path), result :+ position)())
    }

  override def setup(input: List[String]): Graph = Graph(input)

  override def solution1(graph: Graph): Long =
    graph.findAll('X').flatMap(position => dfs(graph, position, "XMAS".toList)()).count(_.nonEmpty)

  override def solution2(graph: Graph): Long = {
    val left = graph.findAll('M').flatMap(position => dfs(graph, position, "MAS".toList)(onlyDiagonalDirections))
    val right = left.map(mas => List(Position(mas.head._1, mas.last._2), mas(1), Position(mas.last._1, mas.head._2)))
    left.map(_.sortBy(_())).intersect(right.map(_.sortBy(_()))).size / 2
  }
}
