package Solutions

import utils.Problem

object Day7 extends Problem[Map[BigInt, List[BigInt]]](2024, 7) {
  private def checkCalculation(values: List[BigInt], total: BigInt, result: BigInt, concat: Boolean = false): Boolean =
    values match {
      case Nil => if (result == total) true else false
      case x :: xs => checkCalculation(xs, total, result + x, concat) || checkCalculation(xs, total, result * x, concat)
        || (if (concat) checkCalculation(xs, total, BigInt(result.toString + x.toString), concat) else false)
    }

  override def parse(list: List[String]): Map[BigInt, List[BigInt]] = list match {
    case s"$total: $values" :: xs => parse(xs) + (BigInt(total) -> values.split(' ').withFilter(_.nonEmpty).map(BigInt(_)).toList)
    case _ => Map.empty
  }

  override def solution1(input: Map[BigInt, List[BigInt]]): BigInt =
    input.withFilter((total, values) => checkCalculation(values.tail, total, values.head)).map(_._1).sum

  override def solution2(input: Map[BigInt, List[BigInt]]): BigInt =
    input.withFilter((total, values) => checkCalculation(values.tail, total, values.head, true)).map(_._1).sum
}
