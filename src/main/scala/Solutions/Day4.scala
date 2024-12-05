package Solutions

import utils.Problem

import scala.annotation.tailrec
import scala.collection.mutable

case class Input(graph: Array[Array[Char]], positions: List[(Int, Int)])

object Day4 extends Problem[Map[String, Input]](2024, 4) {
  private val directions = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))
  private val part2Directions = List((-1, -1), (-1, 1), (1, -1), (1, 1))

  private def checkBounds(graph: Array[Array[Char]], i: Int, j: Int): Boolean =
    (graph.indices contains i) && (graph(i).indices contains j)

  private def dfs(paths: List[(Int, Int)], graph: Array[Array[Char]], target: List[Char], i: Int, j: Int,
                  direction: Option[(Int, Int)] = None, result: List[(Int, Int)] = List.empty): List[List[(Int, Int)]] =
    (target, direction) match {
      case (Nil, _) => List(result)
      case (x :: _, _) if !checkBounds(graph, i, j) || graph(i)(j) != x => Nil
      case (_ :: xs, Some(x, y)) => dfs(paths, graph, xs, i + x, j + y, Some(x, y), result :+ ((i, j)))
      case (_ :: xs, _) => paths.flatMap((x, y) => dfs(paths, graph, xs, i + x, j + y, Some(x, y), result :+ ((i, j))))
    }

  private def getResultLists(positions: List[(Int, Int)], graph: Array[Array[Char]], target: List[Char],
                             paths: List[(Int, Int)]): List[List[(Int, Int)]] =
    positions.flatMap((i, j) => dfs(paths, graph, target, i, j))

  override def parse(input: List[String]): Map[String, Input] = {
    def getGraph: Array[Array[Char]] = input.toArray.map(_.toCharArray)

    def getPositions(head: Char): List[(Int, Int)] =
      input.zipWithIndex.flatMap((str, i) => str.zipWithIndex.withFilter(_._1 == head).map((_, j) => (i, j)))

    Map("XMAS" -> Input(getGraph, getPositions('X')), "MAS" -> Input(getGraph, getPositions('M')))
  }

  override def solution1(input: Map[String, Input]): Int =
    getResultLists(input("XMAS").positions, input("XMAS").graph, "XMAS".toList, directions).size

  override def solution2(input: Map[String, Input]): Int = {
    @tailrec
    def temp(results: Set[List[(Int, Int)]], count: Int = 0): Int = if (results.isEmpty) count else
      results.head match {
        case List(x, y, z) if results.contains(List((x._1, z._2), y, (z._1, x._2)).sorted) =>
          temp(results - List(x, y, z) - List((x._1, z._2), y, (z._1, x._2)).sorted, count + 1)
        case _ => 0
      }

    temp(getResultLists(input("MAS").positions, input("MAS").graph, "MAS".toList, part2Directions).toSet)
  }
}

/*
original: List((0,1), (1,2), (2,3))
row: List((0,3), (1,2), (1,2))
*/