package org.advent.year2024

import org.advent.utils.{Graph, Position, Problem, graphDirections}

import scala.annotation.tailrec

object Day18 extends Problem[(Int, List[Position])](2024, 18) {
  @tailrec
  private def walkBack(paths: Map[Position, Position], start: Position, end: Position, count: Long = 0): Long =
    if (start == end) count else walkBack(paths, start, paths(end), count + 1)

  @tailrec
  def bfs(graph: Graph, finish: Position, queue: List[Position] = List.empty, paths: Map[Position, Position] = Map.empty): Option[Long] =
    queue match {
      case Nil => None
      case x :: _ if x == finish => Some(walkBack(paths, Position(0, 0), finish))
      case x :: xs =>
        val children = graphDirections.withFilter(next => graph.checkBounds(queue.head + next) && graph(queue.head + next) == '.').map(_ + queue.head)
        children.foreach(graph.update(_, 'O'))
        bfs(graph, finish, xs ::: children, children.foldLeft(paths)((parents, child) => parents.updated(child, queue.head)))
    }

  private def getGraph(coordinates: List[Position], bytes: Int): Graph = {
    val graph = Graph(List.fill(coordinates.maxBy(_.x).x + 1)((0 to coordinates.maxBy(_.y).y).foldLeft("")((sum, _) => sum + '.')))
    coordinates.take(bytes).foreach(coordinate => graph.update(coordinate, '#'))
    graph
  }

  override def setup(input: List[String]): (Int, List[Position]) =
    (input.head.toInt, input.tail.map(_.split(",")).map(xs => Position(xs.last.toInt, xs.head.toInt)))

  override def solution1(input: (Int, List[Position])): Long = {
    val graph: Graph = getGraph(input._2, input._1)
    graph.update(Position(0, 0), 'O')
    bfs(graph, graph.length - Position(1, 1), List(Position(0, 0))).get
  }

  override def solution2(input: (Int, List[Position])): Position = {
    @tailrec
    def collectFirstCoordinate(coordinates: List[Position], graph: Graph): Position = coordinates match {
      case Nil => coordinates.last
      case x :: xs => graph.update(x, '#'); val nextGraph = Graph(graph.graph.map(_.mkString).toList)
        if (bfs(nextGraph, nextGraph.length - Position(1, 1), List(Position(0, 0))).isEmpty) x else collectFirstCoordinate(xs, graph)
    }

    collectFirstCoordinate(input._2, getGraph(input._2, 0)).swap
  }
}
