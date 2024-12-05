package Solutions

import utils.Problem
import scala.annotation.tailrec

object Day5 extends Problem[(Map[Int, Set[Int]], List[List[Int]])](2024, 5) {
  @tailrec
  private def fixListRange(list: List[Int], graph: Map[Int, Set[Int]], fixOrder: Boolean = false,
                           result: List[Int] = List.empty): List[Int] = list match {
    case Nil => result
    case x :: xs if xs.count(elem => graph(x).contains(elem)) == xs.size => fixListRange(xs, graph, fixOrder, result :+ x)
    case x :: xs if fixOrder =>
      val (contains, notContains) = xs.partition(elem => graph(x).contains(elem))
      fixListRange(notContains ::: (x :: contains), graph, fixOrder, result)
    case _ => Nil
  }

  override def parse(list: List[String]): (Map[Int, Set[Int]], List[List[Int]]) = {
    val parser = """(\d+)\|(\d+)""".r

    @tailrec
    def parseInput(input: List[String], graph: Map[Int, Set[Int]] = Map.empty.withDefaultValue(Set.empty),
                   result: List[List[Int]] = List.empty): (Map[Int, Set[Int]], List[List[Int]]) =
      input match {
        case Nil => (graph, result)
        case x :: xs => x match {
          case parser(x, y) => parseInput(xs, graph.updated(x.toInt, graph(x.toInt) + y.toInt), result)
          case "" => parseInput(xs, graph, result)
          case list => parseInput(xs, graph, result :+ list.split(',').withFilter(_.nonEmpty).map(_.toInt).toList)
        }
      }

    parseInput(list)
  }

  override def solution1(input: (Map[Int, Set[Int]], List[List[Int]])): Int =
    input._2.map(row => fixListRange(row, input._1)).withFilter(_.nonEmpty).map(row => row(row.size / 2)).sum

  override def solution2(input: (Map[Int, Set[Int]], List[List[Int]])): Int = input._2.map(row => {
    val result = fixListRange(row, input._1, true)
    if (fixListRange(row, input._1).nonEmpty) 0 else result(result.size / 2)
  }).sum
}
