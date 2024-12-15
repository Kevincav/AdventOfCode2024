package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day15Test extends AnyFunSuite with Matchers {
  test("Day 15 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day15.solution1(Day15.setup(data)) shouldBe 0
  }

  test("Day 15 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day15.solution2(Day15.setup(data)) shouldBe 0
  }

  test("Run Day 15") {
    Day15.run().unsafeRunSync()
  }
}
