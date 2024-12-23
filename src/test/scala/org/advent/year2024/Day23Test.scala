package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day23Test extends AnyFunSuite with Matchers {
  test("Day 23 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day23.solution1(Day23.setup(data)) shouldBe 0
  }

  test("Day 23 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day23.solution2(Day23.setup(data)) shouldBe 0
  }

  test("Run Day 23") {
    Day23.run().unsafeRunSync()
  }
}
