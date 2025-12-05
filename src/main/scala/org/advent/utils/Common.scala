package org.advent.utils

import scala.annotation.targetName

case class Position(x: Int, y: Int) {
  @targetName("add")
  def +(position: Position): Position = Position(x + position.x, y + position.y)
  @targetName("sub")
  def -(position: Position): Position = Position(x - position.x, y - position.y)
  @targetName("mul")
  def *(position: Position): Position = Position(x * position.x, y * position.y)
  @targetName("div")
  def /(position: Position): Position = Position(x / position.x, y / position.y)
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
  def abs: Position = Position(math.abs(x), math.abs(y))
  def swap: Position = Position(y, x)
  def sum: Int = x + y
  def max: Int = math.max(x, y)
  def min(position: Position): Position = Position(math.min(x, position.x), math.min(y, position.y))
}

sealed trait Direction {
  val forward: Position
  def getLeft: Direction
  def getRight: Direction
}

case class North(forward: Position = Position(-1, 0)) extends Direction {
  override def getLeft: Direction = West()
  override def getRight: Direction = East()
}

case class South(forward: Position = Position(1, 0)) extends Direction {
  override def getLeft: Direction = East()
  override def getRight: Direction = West()
}

case class East(forward: Position = Position(0, 1)) extends Direction {
  override def getLeft: Direction = North()
  override def getRight: Direction = South()
}

case class West(forward: Position = Position(0, -1)) extends Direction {
  override def getLeft: Direction = South()
  override def getRight: Direction = North()
}

case class Robot(position: Position, direction: Direction, weight: Long = 0) {
  def moveForward(): Robot = this.copy(position = position + direction.forward, weight = weight + 1)
  def moveLeft(): Robot = this.copy(direction = direction.getLeft, weight = weight + 1000).moveForward()
  def moveRight(): Robot = this.copy(direction = direction.getRight, weight = weight + 1000).moveForward()
}

case class Graph(input: List[String]) {
  val graph: Array[Array[Char]] = input.map(_.toArray).toArray
  private val ranges = (graph.indices, graph.head.indices)
  def apply(): Array[Array[Char]] = graph
  def apply(i: Int): Array[Char] = graph(i)
  def apply(i: Int, j: Int): Char = graph(i)(j)
  def apply(position: Position): Char = graph(position.x)(position.y)
  def update(position: Position, value: Char): Unit = graph(position.x)(position.y) = value
  def checkBounds(position: Position): Boolean = (ranges._1 contains position.x) && (ranges._2.indices contains position.y)
  def findAll(target: Char): List[Position] =
    graph.zipWithIndex.flatMap((row, i) => row.zipWithIndex.withFilter(_._1 == target).map((_, j) => Position(i, j))).toList
  def length: Position = Position(graph.length, graph.last.length)
  override def toString: String = "." ++ graph.indices.map(_ % 10).mkString ++ "\n" ++
    graph.map(_.mkString).zipWithIndex.map((a, b) => (b % 10).toString ++ a).mkString("\n")
}

val diagonalGraphDirections: List[Position] = List(
  Position(-1, -1), Position(-1, 0), Position(-1, 1), Position(0, -1),
  Position(0, 1), Position(1, -1), Position(1, 0), Position(1, 1)
)

val graphDirections: List[Position] = List(Position(-1, 0), Position(1, 0), Position(0, -1), Position(0, 1))
val cardinalDirections: List[Direction] = List(North(), South(), East(), West())
val onlyDiagonalDirections: List[Position] = List(Position(-1, -1), Position(-1, 1), Position(1, -1), Position(1, 1))
val allDirections = onlyDiagonalDirections ::: graphDirections
