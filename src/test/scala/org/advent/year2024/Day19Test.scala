package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day19Test extends AnyFunSuite with Matchers {
  test("Day 19 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day19.solution1(Day19.setup(data)) shouldBe 0
  }

  test("Day 19 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day19.solution2(Day19.setup(data)) shouldBe 0
  }

  test("Run Day 19") {
    Day19.run().unsafeRunSync()
  }
}
