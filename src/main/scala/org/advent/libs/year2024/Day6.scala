package org.advent.libs.year2024

import org.advent.utils.Position

object Day6 {
  val directions: Map[Char, Set[Direction]] = Map('^' -> Set(North), '>' -> Set(East), 'v' -> Set(South), '<' -> Set(West),
    180.toChar -> Set(East, South), 178.toChar -> Set(South, West), 154.toChar -> Set(West, North), 156.toChar -> Set(North, East))

  sealed trait Direction {
    def position: Position
    def nextDirection: Direction
    def direction: Char
  }

  case class Guard(position: Position, direction: Direction) {
    def moveForward: Guard = copy(position = position + direction.position)
    def turnRight: Guard = copy(direction = direction.nextDirection)
  }

  private case object North extends Direction {
    override val position: Position = Position(-1, 0)
    override val nextDirection: Direction = East
    override val direction: Char = '^'
  }

  private case object South extends Direction {
    override val position: Position = Position(1, 0)
    override val nextDirection: Direction = West
    override val direction: Char = 'v'
  }

  private case object East extends Direction {
    override val position: Position = Position(0, 1)
    override val nextDirection: Direction = South
    override val direction: Char = '>'
  }

  private case object West extends Direction {
    override val position: Position = Position(0, -1)
    override val nextDirection: Direction = North
    override val direction: Char = '<'
  }
}
