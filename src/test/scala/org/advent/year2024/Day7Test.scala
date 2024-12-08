package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite

class Day7Test extends AnyFunSuite {
  test("Test Day 7 Part 1") {
    val data = s"""|190: 10 19
                   |3267: 81 40 27
                   |83: 17 5
                   |156: 15 6
                   |7290: 6 8 6 15
                   |161011: 16 10 13
                   |192: 17 8 14
                   |21037: 9 7 18 13
                   |292: 11 6 16 20""".stripMargin.split("\n").toList

    assert(Day7.solution1(Day7.setup(data)) == 3749)
  }

  test("Test Day 7 Part 2") {
    val data =
      s"""|190: 10 19
          |3267: 81 40 27
          |83: 17 5
          |156: 15 6
          |7290: 6 8 6 15
          |161011: 16 10 13
          |192: 17 8 14
          |21037: 9 7 18 13
          |292: 11 6 16 20""".stripMargin.split("\n").toList

    assert(Day7.solution2(Day7.setup(data)) == 11387)
  }

  test("Run Day 7") {
    if (sys.env.contains("PRODUCTION")) Day7.run()
  }
}

