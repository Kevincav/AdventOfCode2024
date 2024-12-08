package org.advent.year2024

import org.advent.libs.year2024.Day6._
import org.advent.utils.{Graph, Position, Problem}

import scala.annotation.tailrec

object Day6 extends Problem[(Graph, Int)](2024, 6) {
  private def moveForwardOrTurn(guard: Guard)(implicit graph: Graph): Guard = guard match {
    case _ if !graph.checkBounds(guard.moveForward.position) => guard.moveForward
    case guard if graph(guard.moveForward.position) == '#' =>
      graph(guard.position) = (guard.direction.direction + guard.direction.nextDirection.direction).toChar
      guard.turnRight
    case guard if graph(guard.moveForward.position) != '.' => graph(guard.moveForward.position) = '+'
      guard.moveForward
    case guard => graph(guard.moveForward.position) = guard.direction.direction
      guard.moveForward
  }

  @tailrec
  private def checkRightDirection(guard: Guard)(implicit graph: Graph): Boolean =
    if (!graph.checkBounds(guard.position) || graph(guard.position) == '#') false
    else if (graph(guard.position) == '.' || graph(guard.position) == '+') checkRightDirection(guard.moveForward)
    else if (directions(graph(guard.position)).contains(guard.direction)) true
    else false

  @tailrec
  private def dfs(guard: Guard, counter: Int = 0)(implicit graph: Graph): (Graph, Int) =
    moveForwardOrTurn(guard) match {
      case Guard(position, _) if !graph.checkBounds(position) => (graph, counter)
      case newGuard: Guard => dfs(newGuard, counter + (if (checkRightDirection(guard.turnRight)) 1 else 0))
    }

  private def getStartingLocation(graph: Graph): Guard = graph.graph.zipWithIndex
    .flatMap((row, i) => row.zipWithIndex.withFilter((char, _) => directions.keySet.contains(char))
      .map((char, j) => Guard(Position(i, j), directions(char).head))).head

  override def setup(list: List[String]): (Graph, Int) = {
    implicit val graph: Graph = Graph(list.map(_.toArray).toArray)
    val startingGuard = getStartingLocation(graph)
    graph(startingGuard.position) = startingGuard.direction.direction
    dfs(startingGuard)
  }

  override def solution1(input: (Graph, Int)): Int =
    input._1.graph.map(_.count(char => (directions.keySet + '+').contains(char) )).sum

  override def solution2(input: (Graph, Int)): Int = input._2
}
