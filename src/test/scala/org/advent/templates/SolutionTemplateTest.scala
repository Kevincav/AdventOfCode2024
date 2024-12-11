package org.advent.templates

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import cats.effect.unsafe.implicits.global

class SolutionTemplateTest extends AnyFunSuite with Matchers {
  test("Test Day Template Part 1") {
    val data = s"""|""".stripMargin.split("\n").toList

    SolutionTemplate.solution1(SolutionTemplate.setup(data)) shouldBe 0
  }

  test("Test Day Template Part 2") {
    val data = s"""|""".stripMargin.split("\n").toList

    SolutionTemplate.solution2(SolutionTemplate.setup(data)) shouldBe 0
  }

  test("Run Day Template") {
    SolutionTemplate.run().unsafeRunSync()
  }
}
