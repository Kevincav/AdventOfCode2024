package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day22Test extends AnyFunSuite with Matchers {
  test("Day 22 Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day22.solution1(Day22.setup(data)) shouldBe 0
  }

  test("Day 22 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day22.solution2(Day22.setup(data)) shouldBe 0
  }

  test("Run Day 22") {
    Day22.run().unsafeRunSync()
  }
}
