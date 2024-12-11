package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day6Test extends AnyFunSuite with Matchers {
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

    Day6.solution1(Day6.setup(data)) shouldBe 41
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

    Day6.solution2(Day6.setup(data)) shouldBe 6
  }

  test("Run Day 6") {
    Day6.run().unsafeRunSync()
  }
}
