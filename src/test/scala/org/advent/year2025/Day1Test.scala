package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day1Test extends AnyFunSuite with Matchers {
  test("Day 1 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day1.solution1(Day1.setup(data)) shouldBe 0
  }

  test("Day 1 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day1.solution2(Day1.setup(data)) shouldBe 0
  }

  test("Run Day 1") {
    Day1.run().unsafeRunSync()
  }
}
