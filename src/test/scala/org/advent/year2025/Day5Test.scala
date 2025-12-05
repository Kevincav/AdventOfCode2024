package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day5Test extends AnyFunSuite with Matchers {
  test("Day 5 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day5.solution1(Day5.setup(data)) shouldBe 0
  }

  test("Day 5 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day5.solution2(Day5.setup(data)) shouldBe 0
  }

  test("Run Day 5") {
    Day5.run().unsafeRunSync()
  }
}
