package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day17Test extends AnyFunSuite with Matchers {
  test("Day 17 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day17.solution1(Day17.setup(data)) shouldBe 0
  }

  test("Day 17 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day17.solution2(Day17.setup(data)) shouldBe 0
  }

  test("Run Day 17") {
    Day17.run().unsafeRunSync()
  }
}
