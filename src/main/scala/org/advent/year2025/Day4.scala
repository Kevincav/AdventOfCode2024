package org.advent.year2025

import org.advent.utils.{Graph, Position, Problem, allDirections}

import scala.annotation.tailrec

object Day4 extends Problem[(Graph, List[Position])](2025, 4) {
  private def getUpdatedPapers(graph: Graph, sides: Int): List[Position] = graph.findAll('@').filter(current =>
    allDirections.count(next => graph.checkBounds(current + next) && graph(current + next) == '@') < sides)

  override def setup(input: List[String]): (Graph, List[Position]) = {
    val graph = Graph[input]
    (graph, getUpdatedPapers(graph, 4))
  }

  override def solution1(data: (Graph, List[Position])): Long = data._2.length

  override def solution2(data: (Graph, List[Position])): Long = if (data._2.isEmpty) 0 else {
    data._2.foreach(current => data._1(current) = '.')
    val papers = getUpdatedPapers(data._1)
    papers.length + solution1(data.copy(_2 = papers))
  }
}
