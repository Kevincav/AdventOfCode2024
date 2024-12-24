package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day24Test extends AnyFunSuite with Matchers {
  test("Day 24 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day24.solution1(Day24.setup(data)) shouldBe 0
  }

  test("Day 24 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day24.solution2(Day24.setup(data)) shouldBe 0
  }

  test("Run Day 24") {
    Day24.run().unsafeRunSync()
  }
}
