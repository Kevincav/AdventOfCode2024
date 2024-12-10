package org.advent.utils

import scala.annotation.targetName

case class Position(x: Int, y: Int) {
  @targetName("add")
  def +(position: Position): Position = Position(x + position.x, y + position.y)
  @targetName("sub")
  def -(position: Position): Position = Position(x - position.x, y - position.y)
  @targetName("mul")
  def *(i: Int): Position = Position(x * i, y * i)
  @targetName("div")
  def /(i: Int): Position = Position(x / i, y / i)
  @targetName("gt")
  def >(position: Position): Boolean = x > position.x && y > position.y
  @targetName("lt")
  def <(position: Position): Boolean = x < position.x && y < position.y
  @targetName("equals")
  def ==(position: Position): Boolean = x == position.x && y == position.y
  def apply(): (Int, Int) = (x, y)
  def abs(): Position = Position(math.abs(x), math.abs(y))
}

case class Graph(graph: Array[Array[Char]]) {
  private val ranges = (graph.indices, graph.head.indices)
  def apply(position: Position): Char = graph(position.x)(position.y)
  def update(position: Position, value: Char): Unit = graph(position.x)(position.y) = value
  def checkBounds(position: Position): Boolean = (ranges._1 contains position.x) && (ranges._2.indices contains position.y)
  def findAll(target: Char): List[Position] =
    graph.zipWithIndex.flatMap((row, i) => row.zipWithIndex.withFilter(_._1 == target).map((_, j) => Position(i, j))).toList
  override def toString: String = "." ++ graph.indices.map(_ % 10).mkString ++ "\n" ++
    graph.map(_.mkString).zipWithIndex.map((a, b) => (b % 10).toString ++ a).mkString("\n")
}

val diagonalGraphDirections: List[Position] = List(
  Position(-1, -1), Position(-1, 0), Position(-1, 1), Position(0, -1),
  Position(0, 1), Position(1, -1), Position(1, 0), Position(1, 1)
)

val graphDirections: List[Position] = List(Position(-1, 0), Position(1, 0), Position(0, -1), Position(0, 1))
