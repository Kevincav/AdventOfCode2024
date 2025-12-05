package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day3Test extends AnyFunSuite with Matchers {
  test("Day 3 Part 1") {
    val data = s"""|987654321111111
                   |811111111111119
                   |234234234234278
                   |818181911112111""".stripMargin.split("\n").toList

    Day3.solution1(Day3.setup(data)) shouldBe 357
  }

  test("Day 3 Part 2") {
    val data = s"""|987654321111111
                   |811111111111119
                   |234234234234278
                   |818181911112111""".stripMargin.split("\n").toList

    Day3.solution2(Day3.setup(data)) shouldBe 0L // 3121910778619L
  }

  test("Run Day 3") {
    Day3.run().unsafeRunSync()
  }
}
