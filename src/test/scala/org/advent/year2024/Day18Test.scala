package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day18Test extends AnyFunSuite with Matchers {
  test("Day 18 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day18.solution1(Day18.setup(data)) shouldBe 0
  }

  test("Day 18 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day18.solution2(Day18.setup(data)) shouldBe 0
  }

  test("Run Day 18") {
    Day18.run().unsafeRunSync()
  }
}
