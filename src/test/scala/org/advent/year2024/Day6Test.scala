package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite

class Day6Test extends AnyFunSuite {
  test("Test Day 6 Part 1") {
    val data = s"""|....#.....
                   |.........#
                   |..........
                   |..#.......
                   |.......#..
                   |..........
                   |.#..^.....
                   |........#.
                   |#.........
                   |......#...""".stripMargin.split("\n").toList

    assert(Day6.solution1(Day6.setup(data)) == 41)
  }

  test("Test Day 6 Part 2") {
    val data =
      s"""....#.....
         |.........#
         |..........
         |..#.......
         |.......#..
         |..........
         |.#..^.....
         |........#.
         |#.........
         |......#...""".stripMargin.split("\n").toList

    // assert(Day6.solution2(Day6.setup(data)) == 6)
  }

  test("Run Day 6") {
    if (sys.env.contains("PRODUCTION")) Day6.run()
  }
}

