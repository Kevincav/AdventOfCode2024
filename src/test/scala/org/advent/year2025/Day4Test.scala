package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day4Test extends AnyFunSuite with Matchers {
  test("Day 4 Part 1") {
    val data = s"""|..@@.@@@@.
                   |@@@.@.@.@@
                   |@@@@@.@.@@
                   |@.@@@@..@.
                   |@@.@@@@.@@
                   |.@@@@@@@.@
                   |.@.@.@.@@@
                   |@.@@@.@@@@
                   |.@@@@@@@@.
                   |@.@.@@@.@.""".stripMargin.split("\n").toList

    Day4.solution1(Day4.setup(data)) shouldBe 13
  }

  test("Day 4 Part 2") {
    val data = s"""|..@@.@@@@.
                   |@@@.@.@.@@
                   |@@@@@.@.@@
                   |@.@@@@..@.
                   |@@.@@@@.@@
                   |.@@@@@@@.@
                   |.@.@.@.@@@
                   |@.@@@.@@@@
                   |.@@@@@@@@.
                   |@.@.@@@.@.""".stripMargin.split("\n").toList

    Day4.solution2(Day4.setup(data)) shouldBe 43
  }

  test("Run Day 4") {
    Day4.run().unsafeRunSync()
  }
}
