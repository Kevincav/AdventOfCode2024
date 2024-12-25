package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day25Test extends AnyFunSuite with Matchers {
  test("Day 25 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day25.solution1(Day25.setup(data)) shouldBe 0
  }

  test("Day 25 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day25.solution2(Day25.setup(data)) shouldBe 0
  }

  test("Run Day 25") {
    Day25.run().unsafeRunSync()
  }
}
