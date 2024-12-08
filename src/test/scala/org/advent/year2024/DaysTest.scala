package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite

class DaysTest extends AnyFunSuite {
  test("Run Days") {
    if (sys.env.contains("PRODUCTION")) {
      Day1.run()
      Day2.run()
      Day3.run()
      Day4.run()
      Day5.run()
      Day6.run()
      Day7.run()
    }
  }
}
