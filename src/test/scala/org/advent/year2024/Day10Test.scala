package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day10Test extends AnyFunSuite with Matchers {
  test("Day 10 Part 1") {
    val data = s"""|89010123
                   |78121874
                   |87430965
                   |96549874
                   |45678903
                   |32019012
                   |01329801
                   |10456732""".stripMargin.split("\n").toList

    Day10.solution1(Day10.setup(data)) shouldBe 36
  }

  test("Day 10 Part 2") {
    val data = s"""|89010123
                   |78121874
                   |87430965
                   |96549874
                   |45678903
                   |32019012
                   |01329801
                   |10456732""".stripMargin.split("\n").toList

    Day10.solution2(Day10.setup(data)) shouldBe 81
  }

  test("Run Day 10") {
    Day10.run().unsafeRunSync()
  }
}
