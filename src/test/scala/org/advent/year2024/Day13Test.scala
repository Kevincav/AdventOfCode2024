package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

import scala.concurrent.duration.*

class Day13Test extends AnyFunSuite with Matchers {
  test("Day 13 Part 1") {
    val data =
      s"""|Button A: X+94, Y+34
          |Button B: X+22, Y+67
          |Prize: X=8400, Y=5400
          |
          |Button A: X+26, Y+66
          |Button B: X+67, Y+21
          |Prize: X=12748, Y=12176
          |
          |Button A: X+17, Y+86
          |Button B: X+84, Y+37
          |Prize: X=7870, Y=6450
          |
          |Button A: X+69, Y+23
          |Button B: X+27, Y+71
          |Prize: X=18641, Y=10279""".stripMargin.split("\n").toList

    println(0.minutes.fromNow.time)

    Day13.solution1(Day13.setup(data)) shouldBe 480
  }

  test("Day 13 Part 2") {
    val data =
      s"""|Button A: X+94, Y+34
          |Button B: X+22, Y+67
          |Prize: X=8400, Y=5400
          |
          |Button A: X+26, Y+66
          |Button B: X+67, Y+21
          |Prize: X=12748, Y=12176
          |
          |Button A: X+17, Y+86
          |Button B: X+84, Y+37
          |Prize: X=7870, Y=6450
          |
          |Button A: X+69, Y+23
          |Button B: X+27, Y+71
          |Prize: X=18641, Y=10279""".stripMargin.split("\n").toList
    Day13.solution2(Day13.setup(data)) shouldBe 0
  }

  test("Run Day 13") {
    Day13.run().unsafeRunSync()
  }
}
