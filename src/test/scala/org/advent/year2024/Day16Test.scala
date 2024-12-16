package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day16Test extends AnyFunSuite with Matchers {
  test("Day 16 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day16.solution1(Day16.setup(data)) shouldBe 0
  }

  test("Day 16 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day16.solution2(Day16.setup(data)) shouldBe 0
  }

  test("Run Day 16") {
    Day16.run().unsafeRunSync()
  }
}
