package org.advent.year2025

import org.advent.utils.{Graph, Position, Problem, allDirections}

import scala.annotation.tailrec

object Day4 extends Problem[Graph](2025, 4) {
  private def getUpdatedPapers(graph: Graph, sides: Int): List[Position] = graph.findAll('@').filter(current =>
    allDirections.count(next => graph.checkBounds(current + next) && graph(current + next) == '@') < sides)

  override def setup(input: List[String]): Graph = Graph(input)

  override def solution1(data: Graph): Long = getUpdatedPapers(data, 4).length

  override def solution2(data: Graph): Long = {
    @tailrec
    def iterate(count: Long = 0L): Long = {
      val papers = getUpdatedPapers(data, 4)
      if (papers.isEmpty) count else {
        papers.foreach(current => data(current) = '.')
        iterate(count + papers.length)
      }
    }

    iterate()
  }
}
