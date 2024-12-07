package Solutions

import utils.Problem

object Day7 extends Problem[List[(Long, List[Long])]](2024, 7) {
  private def checkCalculation(values: List[Long], total: Long, result: Long, concat: Boolean = false): Boolean =
    values match {
      case Nil => if (result == total) true else false
      case x :: xs => checkCalculation(xs, total, result + x, concat) || checkCalculation(xs, total, result * x, concat)
        || (if (concat) checkCalculation(xs, total, (result.toString + x.toString).toLong, concat) else false)
    }

  override def parse(list: List[String]): List[(Long, List[Long])] =
    list.map { case s"$total: $values" => total.toLong -> values.split(' ').map(_.toLong).toList }

  override def solution1(input: List[(Long, List[Long])]): Long =
    input.withFilter((total, values) => checkCalculation(values.tail, total, values.head)).map(_._1).sum

  override def solution2(input: List[(Long, List[Long])]): Long =
    input.withFilter((total, values) => checkCalculation(values.tail, total, values.head, true)).map(_._1).sum
}
