package org.advent.templates

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day11Test extends AnyFunSuite with Matchers {
  test("Day 11 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day11.solution1(Day11.setup(data)) shouldBe 0
  }

  test("Day 11 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day11.solution2(Day11.setup(data)) shouldBe 0
  }

  test("Run Day 11") {
    Day11.run().unsafeRunSync()
  }
}
