package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class Day9Test extends AnyFunSuite with Matchers {
  test("Test Day 9 Part 1") {
    val data = s"""|2333133121414131402""".stripMargin.split("\n").toList

    Day9.solution1(Day9.setup(data)) shouldBe 1928
  }

  test("Test Day 9 Part 2") {
    val data = s"""|2333133121414131402""".stripMargin.split("\n").toList

    Day9.solution2(Day9.setup(data)) shouldBe 2858
  }

  test("Run Day 9") {
    Day9.run()
  }
}
