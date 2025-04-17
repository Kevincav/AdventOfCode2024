package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day14Test extends AnyFunSuite with Matchers {
  test("Day 14 Part 1") {
    val data = s"""|p=0,4 v=3,-3
                   |p=6,3 v=-1,-3
                   |p=10,3 v=-1,2
                   |p=2,0 v=2,-1
                   |p=0,0 v=1,3
                   |p=3,0 v=-2,-2
                   |p=7,6 v=-1,-3
                   |p=3,0 v=-1,-2
                   |p=9,3 v=2,3
                   |p=7,3 v=-1,2
                   |p=2,4 v=2,-3
                   |p=9,5 v=-3,-3""".stripMargin.split("\n").toList

    Day14.solution1(Day14.setup(data)) shouldBe 12
  }

  test("Day 14 Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    Day14.solution2(Day14.setup(data)) shouldBe 0
  }

  test("Run Day 14") {
    Day14.run().unsafeRunSync()
  }
}
