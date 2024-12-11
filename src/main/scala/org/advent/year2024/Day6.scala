package org.advent.year2024

import org.advent.libs.year2024.Day6.*
import org.advent.utils.{Graph, Position, Problem}

import scala.annotation.{tailrec, targetName}

object Day6 extends Problem[(Graph, Int)](2024, 6) {
  extension (a: Int)
    @targetName("addBool")
    inline def +(b: Boolean): Int = if (b) a + 1 else a

  private def moveForwardOrTurn(graph: Graph, guard: Guard, log: Boolean = true): Guard = guard match {
    case _ if !graph.checkBounds(guard.moveForward.position) => guard.moveForward
    case guard if graph(guard.moveForward.position) == '#' => if (log) graph(guard.position) = '+'; guard.turnRight
    case guard if graph(guard.position) != '.' => if (log) graph(guard.position) = '+'; guard.moveForward
    case guard => if (log) graph(guard.position) = guard.direction.direction; guard.moveForward
  }

  @tailrec
  private def checkDfs(graph: Graph, guard: Guard, visited: Set[Guard]): Boolean =
    moveForwardOrTurn(graph, guard, false) match {
      case Guard(position, _) if !graph.checkBounds(position) => false
      case newGuard: Guard if visited.contains(newGuard) => true
      case newGuard: Guard => checkDfs(graph, newGuard, visited + guard)
    }

  @tailrec
  private def dfs(graph: Graph, guard: Guard, counter: Int = 0, visited: Set[Guard] = Set.empty): (Graph, Int) =
    moveForwardOrTurn(graph, guard) match {
      case Guard(position, _) if !graph.checkBounds(position) => (graph, counter)
      case newGuard: Guard => dfs(graph, newGuard, counter + checkDfs(graph, guard.turnRight, visited), visited + guard)
    }

  override def setup(list: List[String]): (Graph, Int) = {
    val graph: Graph = Graph(list)
    val startingGuard = directions.map((char, dir) => (dir, graph.findAll(char)))
      .withFilter(_._2.nonEmpty).map((dir, list) => Guard(list.head, dir)).head
    graph(startingGuard.position) = startingGuard.direction.direction
    dfs(graph, startingGuard)
  }

  override def solution1(input: (Graph, Int)): Int =
    input._1().map(_.count(char => (directions.keySet + '+').contains(char) )).sum + 1

  override def solution2(input: (Graph, Int)): Int = input._2
}
