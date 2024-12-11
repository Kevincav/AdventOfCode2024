package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day3Test extends AnyFunSuite with Matchers {
  test("Test Day 3 Part 1") {
    val data = s"""|xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))""".stripMargin.split("\n").toList

    Day3.solution1(Day3.setup(data)) shouldBe 161
  }

  test("Test Day 3 Part 2") {
    val data =
      s"""|xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))""".stripMargin.split("\n").toList

    Day3.solution2(Day3.setup(data)) shouldBe 48
  }

  test("Run Day 3") {
    Day3.run().unsafeRunSync()
  }
}
