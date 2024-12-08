package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite

class Day1Test extends AnyFunSuite {
  test("Test Day 1 Part 1") {
    val data = s"""|3   4
                   |4   3
                   |2   5
                   |1   3
                   |3   9
                   |3   3""".stripMargin.split("\n").toList

    assert(Day1.solution1(Day1.setup(data)) == 11)
  }

  test("Test Day 1 Part 2") {
    val data = s"""|3   4
                   |4   3
                   |2   5
                   |1   3
                   |3   9
                   |3   3""".stripMargin.split("\n").toList

    assert(Day1.solution2(Day1.setup(data)) == 31)
  }

  test("Run Day 1") {
    if (sys.env.contains("PRODUCTION")) Day2.run()
  }
}

