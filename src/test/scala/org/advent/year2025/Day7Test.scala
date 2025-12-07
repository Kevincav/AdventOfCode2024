package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day7Test extends AnyFunSuite with Matchers {
  test("Day 7 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day7.solution1(Day7.setup(data)) shouldBe 0
  }

  test("Day 7 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day7.solution2(Day7.setup(data)) shouldBe 0
  }

  test("Run Day 7") {
    Day7.run().unsafeRunSync()
  }
}
