package org.advent.year2025

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class Day2Test extends AnyFunSuite with Matchers {
  test("Day 2 Part 1") {
    val data = s"""|11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124""".stripMargin.split("\n").toList

    Day2.solution1(Day2.setup(data)) shouldBe 1227775554L
  }

  test("Day 2 Part 2") {
    val data = s"""|11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124""".stripMargin.split("\n").toList

    Day2.solution2(Day2.setup(data)) shouldBe 4174379265L
  }

  test("Run Day 2") {
    Day2.run().unsafeRunSync()
  }
}
