package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class Day5Test extends AnyFunSuite with Matchers {
  test("Test Day 5 Part 1") {
    val data = s"""|47|53
                   |97|13
                   |97|61
                   |97|47
                   |75|29
                   |61|13
                   |75|53
                   |29|13
                   |97|29
                   |53|29
                   |61|53
                   |97|53
                   |61|29
                   |47|13
                   |75|47
                   |97|75
                   |47|61
                   |75|61
                   |47|29
                   |75|13
                   |53|13
                   |
                   |75,47,61,53,29
                   |97,61,53,29,13
                   |75,29,13
                   |75,97,47,61,53
                   |61,13,29
                   |97,13,75,29,47""".stripMargin.split("\n").toList

    Day5.solution1(Day5.setup(data)) shouldBe 143
  }

  test("Test Day 5 Part 2") {
    val data =
      s"""|47|53
          |97|13
          |97|61
          |97|47
          |75|29
          |61|13
          |75|53
          |29|13
          |97|29
          |53|29
          |61|53
          |97|53
          |61|29
          |47|13
          |75|47
          |97|75
          |47|61
          |75|61
          |47|29
          |75|13
          |53|13
          |
          |75,47,61,53,29
          |97,61,53,29,13
          |75,29,13
          |75,97,47,61,53
          |61,13,29
          |97,13,75,29,47""".stripMargin.split("\n").toList

    Day5.solution2(Day5.setup(data)) shouldBe 123
  }

  test("Run Day 5") {
    Day5.run()
  }
}
