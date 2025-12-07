package org.advent.year2025

import org.advent.utils.{Graph, Position, Problem, Robot, South}

import scala.annotation.tailrec
import scala.collection.mutable

object Day7 extends Problem[Graph](2025, 7) {
  override def setup(input: List[String]): Graph = Graph(input)

  override def solution1(data: Graph): Long = {
    @tailrec
    def pushForwardAndSplit(robots: Set[Robot], results: Set[Robot] = Set.empty): Long = {
      val newRobots = robots.filter(robot => data.checkBounds(robot.position))
      if (newRobots.isEmpty) results.size else {
        val splits = newRobots.filter(robot => data(robot.position) == '^')
        pushForwardAndSplit(newRobots.flatMap(robot => if (data(robot.position) == '^')
          Set(robot.stepWest(), robot.stepEast()) else Set(robot.moveForward())), results union splits)
      }
    }

    pushForwardAndSplit(data.findAll('S').map(position => Robot(position, South())).toSet)
  }

  override def solution2(data: Graph): Long = {
    val visited: mutable.Map[Position, Long] = mutable.Map.empty

    def pushForwardAndSplit(robot: Robot): Long =
      if (!data.checkBounds(robot.position)) 1L
      else if (visited.contains(robot.position)) visited(robot.position)
      else {
        val result = if (data(robot.position) == '^') pushForwardAndSplit(robot.stepWest()) + pushForwardAndSplit(robot.stepEast())
        else pushForwardAndSplit(robot.moveForward())
        visited(robot.position) = result
        result
      }

    pushForwardAndSplit(data.findAll('S').map(position => Robot(position, South())).head)
  }
}
