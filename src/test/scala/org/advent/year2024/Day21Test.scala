package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day21Test extends AnyFunSuite with Matchers {
  test("Day 21 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day21.solution1(Day21.setup(data)) shouldBe 0
  }

  test("Day 21 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day21.solution2(Day21.setup(data)) shouldBe 0
  }

  test("Run Day 21") {
    Day21.run().unsafeRunSync()
  }
}
