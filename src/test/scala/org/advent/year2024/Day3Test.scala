package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite

class Day3Test extends AnyFunSuite {
  test("Test Day 3 Part 1") {
    val data = s"""|xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))""".stripMargin.split("\n").toList

    assert(Day3.solution1(Day3.setup(data)) == 161)
  }

  test("Test Day 3 Part 2") {
    val data =
      s"""|xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))""".stripMargin.split("\n").toList

    assert(Day3.solution2(Day3.setup(data)) == 48)
  }

  test("Run Day 3") {
    if (sys.env.contains("PRODUCTION")) Day3.run()
  }
}

