package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day17Test extends AnyFunSuite with Matchers {
  test("Day 17 Part 1") {
    val data = s"""|Register A: 729
                   |Register B: 0
                   |Register C: 0
                   |
                   |Program: 0,1,5,4,3,0""".stripMargin.split("\n").toList

    Day17.solution1(Day17.setup(data)) shouldBe "4,6,3,5,6,3,5,2,1,0"
  }

  test("Day 17 Part 2") {
    val data = s"""|Register A: 2024
                   |Register B: 0
                   |Register C: 0
                   |
                   |Program: 0,3,5,4,3,0""".stripMargin.split("\n").toList

    Day17.solution2(Day17.setup(data)) shouldBe 117440
  }

  test("Run Day 17") {
    Day17.run().unsafeRunSync()
  }
}
