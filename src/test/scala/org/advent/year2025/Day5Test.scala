package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day5Test extends AnyFunSuite with Matchers {
  test("Day 5 Part 1") {
    val data = s"""|3-5
                   |10-14
                   |16-20
                   |12-18
                   |
                   |1
                   |5
                   |8
                   |11
                   |17
                   |32""".stripMargin.split("\n").toList

    Day5.solution1(Day5.setup(data)) shouldBe 3
  }

  test("Day 5 Part 2") {
    val data = s"""|3-5
                   |10-14
                   |16-20
                   |12-18
                   |
                   |1
                   |5
                   |8
                   |11
                   |17
                   |32""".stripMargin.split("\n").toList

    Day5.solution2(Day5.setup(data)) shouldBe 14
  }

  test("Run Day 5") {
    Day5.run().unsafeRunSync()
  }
}
