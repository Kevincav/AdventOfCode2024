package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class Day4Test extends AnyFunSuite with Matchers {
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

    Day4.solution1(Day4.setup(data)) shouldBe 18
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

    Day4.solution2(Day4.setup(data)) shouldBe 9
  }

  test("Run Day 4") {
    Day4.run()
  }
}
