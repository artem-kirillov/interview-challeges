package com.leetcode

import org.scalatest.{FlatSpec, Matchers}

class SlidingWindowMaximumSpec extends FlatSpec with Matchers {
  "SlidingWindowMaximum" should "find sliding window maximum" in {
    val f: (Array[Int], Int) => Array[Int] = SlidingWindowMaximum.Solution.maxSlidingWindow

    f(Array.empty, 0) shouldBe Array.empty
    f(Array(5, 4, 3, 2, 1), 3) should contain theSameElementsInOrderAs Array(5, 4, 3)
    f(Array(5, 4, 3, 2, 1), 2) should contain theSameElementsInOrderAs Array(5, 4, 3, 2)
    f(Array(1,2,3,4,5), 3) should contain theSameElementsInOrderAs Array(3, 4, 5)

    f(Array(1,3,1,2,0,5), 3) should contain theSameElementsInOrderAs Array(3,3,2,5)
  }
}
