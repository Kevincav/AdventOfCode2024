package org.advent.year2024

import org.advent.utils.Problem

import scala.annotation.tailrec

case class Registers(a: Int, b: Int, c: Int, pointer: Int = 0, out: List[Int] = List.empty)

object Day17 extends Problem[(Registers, List[Int])](2024, 17) {
  private sealed trait Instruction {
    val operand: Int

    def execute(registers: Registers): Registers

    def combo(operand: Int, registers: Registers): Int = operand match {
      case value if 0 to 3 contains value => value
      case value if value == 4 => registers.a
      case value if value == 5 => registers.b
      case value if value == 6 => registers.c
    }
  }

  private case class adv(operand: Int) extends Instruction {
    override def execute(registers: Registers): Registers =
      registers.copy(a = registers.a / math.pow(2, combo(operand, registers)).toInt)
  }

  private case class bxl(operand: Int) extends Instruction {
    override def execute(registers: Registers): Registers = registers.copy(b = registers.b ^ operand)
  }

  private case class bst(operand: Int) extends Instruction {
    override def execute(registers: Registers): Registers = registers.copy(b = combo(operand, registers) % 8)
  }

  private case class jnz(operand: Int) extends Instruction {
    override def execute(registers: Registers): Registers =
      registers.copy(pointer = if (registers.a == 0) registers.pointer else operand - 2)
  }

  private case class bxc(operand: Int) extends Instruction {
    override def execute(registers: Registers): Registers = registers.copy(b = registers.b ^ registers.c)
  }

  private case class out(operand: Int) extends Instruction {
    override def execute(registers: Registers): Registers =
      registers.copy(out = registers.out :+ combo(operand, registers) % 8)
  }

  private case class bdv(operand: Int) extends Instruction {
    override def execute(registers: Registers): Registers = registers.copy(b = adv(operand).execute(registers).a)
  }

  private case class cdv(operand: Int) extends Instruction {
    override def execute(registers: Registers): Registers = registers.copy(c = adv(operand).execute(registers).a)
  }

  private def lookupInstruction(opcode: Int, operand: Int): Instruction = opcode match {
    case 0 => adv(operand)
    case 1 => bxl(operand)
    case 2 => bst(operand)
    case 3 => jnz(operand)
    case 4 => bxc(operand)
    case 5 => out(operand)
    case 6 => bdv(operand)
    case 7 => cdv(operand)
  }

  @tailrec
  private def executeInstructions(registers: Registers, instructions: List[Int]): Registers =
    if (registers.pointer >= instructions.size) registers else {
      val result = lookupInstruction(instructions(registers.pointer), instructions(registers.pointer + 1)).execute(registers)
      executeInstructions(result.copy(pointer = result.pointer + 2), instructions)
    }

  override def setup(input: List[String]): (Registers, List[Int]) = (
    Registers(input.head.split(':').last.trim.toInt, input(1).split(':').last.trim.toInt, input(2).split(':').last.trim.toInt),
    input.last.split(':').last.trim.split(',').map(_.toInt).toList)

  override def solution1(data: (Registers, List[Int])): String = {
    executeInstructions(data._1, data._2).out.mkString(",")
  }

  override def solution2(data: (Registers, List[Int])): Long = LazyList.from(0).map(i => executeInstructions(data._1.copy(
      a = i), data._2)).zipWithIndex.collectFirst { case registers if registers._1.out == data._2 => registers }.get._2
}
