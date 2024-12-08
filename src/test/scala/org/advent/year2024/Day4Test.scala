package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite

class Day4Test extends AnyFunSuite {
  test("Test Day 4 Part 1") {
    val data = s"""|MMMSXXMASM
                   |MSAMXMSMSA
                   |AMXSXMAAMM
                   |MSAMASMSMX
                   |XMASAMXAMM
                   |XXAMMXXAMA
                   |SMSMSASXSS
                   |SAXAMASAAA
                   |MAMMMXMMMM
                   |MXMXAXMASX""".stripMargin.split("\n").toList

    assert(Day4.solution1(Day4.setup(data)) == 18)
  }

  test("Test Day 4 Part 2") {
    val data =
      s"""|MMMSXXMASM
          |MSAMXMSMSA
          |AMXSXMAAMM
          |MSAMASMSMX
          |XMASAMXAMM
          |XXAMMXXAMA
          |SMSMSASXSS
          |SAXAMASAAA
          |MAMMMXMMMM
          |MXMXAXMASX""".stripMargin.split("\n").toList

    assert(Day4.solution2(Day4.setup(data)) == 9)
  }

  test("Run Day 4") {
    if (sys.env.contains("PRODUCTION")) Day4.run()
  }
}

