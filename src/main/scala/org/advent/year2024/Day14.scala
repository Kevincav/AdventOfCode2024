package org.advent.year2024

import org.advent.utils.Problem

import scala.util.matching.Regex

private case class Robot(x: Int, y: Int, vert: Int, horiz: Int)

object Day14 extends Problem[(List[Robot], Int, Int)](2024, 14) {
  val regex: Regex = s"""p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)""".r

  private def shiftRobots(robots: List[Robot], vertLimit: Int, horizLimit: Int): List[Robot] = robots.map(robot => robot.copy(x =
    math.abs((robot.x + robot.vert) % vertLimit + vertLimit) % vertLimit, math.abs((robot.y + robot.horiz) % horizLimit + horizLimit) % horizLimit))

  override def setup(input: List[String]): (List[Robot], Int, Int) = {
    val result = input.map { case regex(y, x, j, i) => Robot(x.toInt, y.toInt, i.toInt, j.toInt) }
    (result, result.maxBy(_.x).x + 1, result.maxBy(_.y).y + 1)
  }

  override def solution1(data: (List[Robot], Int, Int)): Long = {
    val result = (0 until 100).foldLeft(data._1)((robots, _) => shiftRobots(robots, data._2, data._3))
    val output = result.filter(robot => robot.x != data._2 / 2 && robot.y != data._3)
      .groupMap(robot => (robot.x, robot.y))(_ => 1).map((pos, list) => (pos, list.size))
    output.withFilter((pos, _) => pos._1 < data._2 / 2 && pos._2 < data._3 / 2).map(_._2).sum *
      output.withFilter((pos, _) => pos._1 < data._2 / 2 && pos._2 > data._3 / 2).map(_._2).sum *
      output.withFilter((pos, _) => pos._1 > data._2 / 2 && pos._2 < data._3 / 2).map(_._2).sum *
      output.withFilter((pos, _) => pos._1 > data._2 / 2 && pos._2 > data._3 / 2).map(_._2).sum
  }

  override def solution2(data: (List[Robot], Int, Int)): Long = 0
}
