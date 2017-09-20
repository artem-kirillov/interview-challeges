package com.leetcode

import org.scalatest.{FlatSpec, Matchers}

class PowerOfThreeSpec extends FlatSpec with Matchers {
  "PowerOfThree" should "test for power of three" in {
    val check = PowerOfThree.Solution.isPowerOfThree _

    check(1) shouldBe true
    check(3) shouldBe true
    check(9) shouldBe true
    check(27) shouldBe true
    check(81) shouldBe true

    check(0) shouldBe false
    check(-1) shouldBe false
    check(-3) shouldBe false
    check(82) shouldBe false
  }
}
