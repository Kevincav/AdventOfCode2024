package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day12Test extends AnyFunSuite with Matchers {
  test("Day 12 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day12.solution1(Day12.setup(data)) shouldBe 0
  }

  test("Day 12 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day12.solution2(Day12.setup(data)) shouldBe 0
  }

  test("Run Day 12") {
    Day12.run().unsafeRunSync()
  }
}
