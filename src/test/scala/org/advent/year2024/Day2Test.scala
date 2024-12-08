package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite

class Day2Test extends AnyFunSuite {
  test("Test Day 2 Part 1") {
    val data = s"""|7 6 4 2 1
                   |1 2 7 8 9
                   |9 7 6 2 1
                   |1 3 2 4 5
                   |8 6 4 4 1
                   |1 3 6 7 9""".stripMargin.split("\n").toList

    assert(Day2.solution1(Day2.setup(data)) == 2)
  }

  test("Test Day 2 Part 2") {
    val data =
      s"""|7 6 4 2 1
          |1 2 7 8 9
          |9 7 6 2 1
          |1 3 2 4 5
          |8 6 4 4 1
          |1 3 6 7 9""".stripMargin.split("\n").toList

    assert(Day2.solution2(Day2.setup(data)) == 4)
  }

  test("Run Day 2") {
    if (sys.env.contains("PRODUCTION")) Day2.run()
  }
}

