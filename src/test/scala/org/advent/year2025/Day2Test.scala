package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day2Test extends AnyFunSuite with Matchers {
  test("Day 2 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day2.solution1(Day2.setup(data)) shouldBe 0
  }

  test("Day 2 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day2.solution2(Day2.setup(data)) shouldBe 0
  }

  test("Run Day 2") {
    Day2.run().unsafeRunSync()
  }
}
