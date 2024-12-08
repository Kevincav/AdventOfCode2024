package org.advent.utils

import scala.annotation.targetName

case class Position(x: Int, y: Int) {
  @targetName("add")
  def +(position: Position): Position = Position(x + position.x, y + position.y)
  @targetName("equals")
  def ==(position: Position): Boolean = x == position.x && y == position.y
  def apply(): (Int, Int) = (x, y)
}

case class Graph(graph: Array[Array[Char]]) {
  def apply(position: Position): Char = graph(position.x)(position.y)
  def update(position: Position, char: Char): Unit = graph(position.x)(position.y) = char
  def checkBounds(position: Position): Boolean =
    (graph.indices contains position.x) && (graph(position.x).indices contains position.y)
}

val diagonalGraphDirections: List[Position] = List(
  Position(-1, -1), Position(-1, 0), Position(-1, 1), Position(0, -1),
  Position(0, 1), Position(1, -1), Position(1, 0), Position(1, 1)
)

val graphDirections: List[Position] = List(Position(-1, -1), Position(-1, 1), Position(1, -1), Position(1, 1))
