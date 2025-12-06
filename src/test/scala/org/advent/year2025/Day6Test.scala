package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day6Test extends AnyFunSuite with Matchers {
  test("Day 6 Part 1") {
    val data = s"""123 328  51 64 \n 45 64  387 23 \n  6 98  215 314\n*   +   *   +  """.split('\n').toList

    Day6.solution1(Day6.setup(data)) shouldBe 4277556
  }

  test("Day 6 Part 2") {
    val data = s"""123 328  51 64 \n 45 64  387 23 \n  6 98  215 314\n*   +   *   +  """.split('\n').toList
    
    Day6.solution2(Day6.setup(data)) shouldBe 3263827
  }

  test("Run Day 6") {
    Day6.run().unsafeRunSync()
  }
}
