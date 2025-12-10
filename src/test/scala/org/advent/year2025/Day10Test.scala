package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day10Test extends AnyFunSuite with Matchers {
  test("Day 10 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day10.solution1(Day10.setup(data)) shouldBe 0
  }

  test("Day 10 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day10.solution2(Day10.setup(data)) shouldBe 0
  }

  test("Run Day 10") {
    Day10.run().unsafeRunSync()
  }
}
