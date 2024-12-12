package org.advent.year2024

import org.advent.utils.{Graph, Position, Problem, graphDirections}

import scala.collection.mutable

object Day10 extends Problem[Graph](2024, 10) {
  private def dfs(graph: Graph, position: Position, value: Int, reverse: Int = 1,
                  visited: mutable.Set[Position] = mutable.Set.empty): Int = {
    visited.add(position)
    if (value == (if (reverse == 1) 9 else 0)) 1 else graphDirections.map {
      case direction if !graph.checkBounds(position + direction) => 0
      case direction if graph(position + direction).asDigit != value + reverse => 0
      case direction if reverse == 1 && visited.contains(position + direction) => 0
      case direction => dfs(graph, position + direction, value + reverse, reverse, visited)
    }.sum
  }

  override def setup(input: List[String]): Graph = Graph(input)

  override def solution1(graph: Graph): Long = graph.findAll('0').map(zero => dfs(graph, zero, 0)).sum

  override def solution2(graph: Graph): Long = graph.findAll('9').map(nine => dfs(graph, nine, 9, -1)).sum
}
