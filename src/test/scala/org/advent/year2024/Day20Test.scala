package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day20Test extends AnyFunSuite with Matchers {
  test("Day 20 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day20.solution1(Day20.setup(data)) shouldBe 0
  }

  test("Day 20 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day20.solution2(Day20.setup(data)) shouldBe 0
  }

  test("Run Day 20") {
    Day20.run().unsafeRunSync()
  }
}
