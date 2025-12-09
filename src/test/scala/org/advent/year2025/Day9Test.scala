package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day9Test extends AnyFunSuite with Matchers {
  test("Day 9 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day9.solution1(Day9.setup(data)) shouldBe 0
  }

  test("Day 9 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day9.solution2(Day9.setup(data)) shouldBe 0
  }

  test("Run Day 9") {
    Day9.run().unsafeRunSync()
  }
}
