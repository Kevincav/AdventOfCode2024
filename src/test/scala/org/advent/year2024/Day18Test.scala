package org.advent.year2024

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import cats.effect.unsafe.implicits.global
import org.advent.utils.Position

class Day18Test extends AnyFunSuite with Matchers {
  test("Day 18 Part 1") {
    val data = s"""|12
                   |5,4
                   |4,2
                   |4,5
                   |3,0
                   |2,1
                   |6,3
                   |2,4
                   |1,5
                   |0,6
                   |3,3
                   |2,6
                   |5,1
                   |1,2
                   |5,5
                   |2,5
                   |6,5
                   |1,4
                   |0,4
                   |6,4
                   |1,1
                   |6,1
                   |1,0
                   |0,5
                   |1,6
                   |2,0""".stripMargin.split("\n").toList

    Day18.solution1(Day18.setup(data)) shouldBe 22
  }

  test("Day 18 Part 2") {
    val data = s"""|12
                   |5,4
                   |4,2
                   |4,5
                   |3,0
                   |2,1
                   |6,3
                   |2,4
                   |1,5
                   |0,6
                   |3,3
                   |2,6
                   |5,1
                   |1,2
                   |5,5
                   |2,5
                   |6,5
                   |1,4
                   |0,4
                   |6,4
                   |1,1
                   |6,1
                   |1,0
                   |0,5
                   |1,6
                   |2,0""".stripMargin.split("\n").toList

    Day18.solution2(Day18.setup(data)) shouldBe Position(6, 1)
  }

  test("Run Day 18") {
    Day18.run().unsafeRunSync()
  }
}
