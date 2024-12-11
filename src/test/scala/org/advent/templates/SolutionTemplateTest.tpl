package org.advent.year{{ aoc_year }}

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day{{ aoc_day }}Test extends AnyFunSuite with Matchers {
  test("Day {{ aoc_day }} Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day{{ aoc_day }}.solution1(Day{{ aoc_day }}.setup(data)) shouldBe 0
  }

  test("Day {{ aoc_day }} Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day{{ aoc_day }}.solution2(Day{{ aoc_day }}.setup(data)) shouldBe 0
  }

  test("Run Day {{ aoc_day }}") {
    Day{{ aoc_day }}.run().unsafeRunSync()
  }
}
