package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day3Test extends AnyFunSuite with Matchers {
  test("Day 3 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day3.solution1(Day3.setup(data)) shouldBe 0
  }

  test("Day 3 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day3.solution2(Day3.setup(data)) shouldBe 0
  }

  test("Run Day 3") {
    Day3.run().unsafeRunSync()
  }
}
