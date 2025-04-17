package org.advent.year2024

import org.advent.utils.{East, Graph, Position, Problem, Robot, cardinalDirections}

import scala.annotation.tailrec

object Day16 extends Problem[(Graph, Position)](2024, 16) {
  override def setup(input: List[String]): (Graph, Position) = {
    val graph = Graph(input)
    (graph, graph.findAll('S').head)
  }

  override def solution1(data: (Graph, Position)): Long = {
    def checkDirection(robot: Robot): Boolean =
      data._1.checkBounds(robot.position) && (data._1(robot.position) == '.' || data._1(robot.position) == 'E')

    @tailrec
    def bfs(queue: List[Robot],
            distances: Map[Position, Long] = Map.empty.withDefaultValue(Long.MaxValue),
            weights: List[Long] = List.empty): Long =
      if (queue.isEmpty) weights.min
      else if (data._1(queue.head.position) == 'E') bfs(queue.tail, distances, weights :+ queue.head.weight)
      else {
        val children = List(queue.head.moveForward(), queue.head.moveLeft(), queue.head.moveRight())
          .filter(checkDirection).filter(direction => distances(direction.position) > direction.weight)
        bfs(queue.tail ::: children, children.foldLeft(distances)((map, robot) => map.updated(robot.position, robot.weight)), weights)
      }

    bfs(List(Robot(data._2, East())))
  }

  override def solution2(data: (Graph, Position)): Long = 0
}
