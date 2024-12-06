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
    val left = getResultLists(input("MAS").positions, input("MAS").graph, "MAS".toList, part2Directions).toSet
    val right = left.map(mas => List((mas.head._1, mas.last._2), mas(1), (mas.last._1, mas.head._2)))
    left.map(_.sorted).intersect(right.map(_.sorted)).size / 2
  }
}
