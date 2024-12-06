package Solutions

import utils.{Graph, Position, Problem}

import scala.annotation.{tailrec, targetName}

object Day6 extends Problem[Graph](2024, 6) {
  private sealed trait Direction {
    def position: Position
    def nextDirection: Direction
  }

  private case object North extends Direction {
    override val position: Position = Position(-1, 0)
    override val nextDirection: Direction = East
  }

  private case object South extends Direction {
    override val position: Position = Position(1, 0)
    override val nextDirection: Direction = West
  }

  private case object East extends Direction {
    override val position: Position = Position(0, 1)
    override val nextDirection: Direction = South
  }

  private case object West extends Direction {
    override val position: Position = Position(0, -1)
    override val nextDirection: Direction = North
  }

  private val directions: Map[Char, Direction] = Map('^' -> North, '>' -> East, 'v' -> South, '<' -> West)

  private case class Guard(position: Position, direction: Direction)

  private def moveForward(graph: Graph, guard: Guard): Guard = {
    val newPosition = guard.position + guard.direction.position
    guard match {
      case _ if !graph.checkBounds(newPosition) => guard.copy(position = newPosition)
      case Guard(position, _) if graph(newPosition) == '#' => guard.copy(direction = guard.direction.nextDirection)
      case Guard(position, _) => graph(newPosition) = 'X'
        guard.copy(position = newPosition)
    }
  }

  @tailrec
  private def dfs(graph: Graph, guard: Guard): Graph =
    moveForward(graph, guard) match {
      case Guard(position, _) if !graph.checkBounds(position) => graph
      case newGuard: Guard => dfs(graph, newGuard)
    }

  private def getStartingLocation(graph: Graph): Guard = graph.graph.zipWithIndex
    .flatMap((row, i) => row.zipWithIndex.withFilter((char, _) => directions.keySet.contains(char))
      .map((char, j) => Guard(Position(i, j), directions(char)))).head

  override def parse(list: List[String]): Graph = Graph(list.map(_.toCharArray).toArray)

  override def solution1(graph: Graph): Int = {
    val startingLocation = getStartingLocation(graph)
    graph(startingLocation.position) = '.'
    dfs(graph, startingLocation).graph.map(_.count(_ == 'X')).sum
  }

  override def solution2(graph: Graph): Int = 3
}