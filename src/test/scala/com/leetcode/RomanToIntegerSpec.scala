package com.leetcode

import org.scalatest.{FlatSpec, Matchers}

class RomanToIntegerSpec extends FlatSpec with Matchers {
  val rti: (String) => Int = RomanToInteger.Solution.romanToInt

  "RomanToInteger" should "convert roman number into arabic one" in {
    rti("I") shouldBe 1
    rti("IV") shouldBe 4
    rti("MCMLIV") shouldBe 1954
    rti("MMIV") shouldBe 2004
    rti("DCXXI") shouldBe 621
  }
}
