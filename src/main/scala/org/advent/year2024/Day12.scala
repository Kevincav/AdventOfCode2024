package org.advent.year2024

import org.advent.utils.{Graph, Position, Problem, graphDirections}

import scala.annotation.tailrec

object Day12 extends Problem[(Graph, Map[Position, Set[Position]])](2024, 12) {
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

  private def checkSides(graph: Graph, positions: List[Position], key: Char, result: Int = 0): Int = positions.size * 4 -
    positions.map(curr => graphDirections.count(pos => graph.checkBounds(curr + pos) && graph(curr + pos) == key)).sum

  private def fetchUniqueLists(list: List[Position], direction: Position): List[Position] =
    list.foldLeft(List(list.head - direction), list.head - direction) { case ((sum, i), j) =>
      if (i + direction == j) (sum, j) else (sum :+ i + direction :+ j - direction, j) }._1 :+ list.last + direction

  private def countUniqueValues(list: List[Int]): Int =
    list.foldLeft(1, list.head - 1) { case ((sum, i), j) => if (i + 1 == j) (sum, j) else (sum + 1, j) }._1

  override def setup(input: List[String]): (Graph, Map[Position, Set[Position]]) = {
    val graph = Graph(input)
    val positions = graph().indices.flatMap(i => graph(i).indices.map(j => Position(i, j))).toList
    (graph, runDfs(graph, positions))
  }

  override def solution1(graphMap: (Graph, Map[Position, Set[Position]])): Long =
    graphMap._2.map((pos, set) => checkSides(graphMap._1, set.toList, graphMap._1(pos)) * set.size).sum

  override def solution2(graphMap: (Graph, Map[Position, Set[Position]])): Long =
    graphMap._2.map((pos, set) => {
      val (vertical, horizontal) = (set.groupMap(_.x)(pos => pos), set.groupMap(_.y)(pos => pos))
      val vert = vertical.flatMap((_, row) => fetchUniqueLists(row.toList.sortBy(_.y), Position(0, 1))).groupMap(_.y)(_.x)
        .flatMap((_, row) => List.fill(row.size / row.toSet.size)(row.toSet.toList)).map(countUniqueValues).sum
      val horz = horizontal.flatMap((_, row) => fetchUniqueLists(row.toList.sortBy(_.x), Position(1, 0))).groupMap(_.x)(_.y)
        .flatMap((_, row) => List.fill(row.size / row.toSet.size)(row.toSet.toList)).map(countUniqueValues).sum
      (vert + horz) * set.size
    }).sum
}
