package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day14Test extends AnyFunSuite with Matchers {
  test("Day 14 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day14.solution1(Day14.setup(data)) shouldBe 0
  }

  test("Day 14 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day14.solution2(Day14.setup(data)) shouldBe 0
  }

  test("Run Day 14") {
    Day14.run().unsafeRunSync()
  }
}
