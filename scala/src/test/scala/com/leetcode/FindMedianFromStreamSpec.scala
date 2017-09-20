package com.leetcode

import org.scalatest.{FlatSpec, Matchers}

class FindMedianFromStreamSpec extends FlatSpec with Matchers {
  "FindMedianFromStream" should "find median in odd sized sequence" in {
    val medianFinder = new FindMedianFromStream.MedianFinder
    Seq(1, 3, 3, 6, 7, 8, 9).foreach(medianFinder.addNum)
    medianFinder.findMedian() shouldBe 6.0 +- 0.0001
  }

  it should "find median in even sized sequence" in {
    val medianFinder = new FindMedianFromStream.MedianFinder
    Seq(1, 2, 3, 4, 5, 6, 8, 9).foreach(medianFinder.addNum)
    medianFinder.findMedian() shouldBe 4.5 +- 0.0001
  }

  it should "pass leetcode test case" in {
    val medianFinder = new FindMedianFromStream.MedianFinder

    medianFinder.addNum(1)
    medianFinder.findMedian() shouldBe 1.0 +- 0.0001

    medianFinder.addNum(2)
    medianFinder.findMedian() shouldBe 1.5 +- 0.0001

    medianFinder.addNum(3)
    medianFinder.findMedian() shouldBe 2.0 +- 0.0001
  }

  it should "pass leetcode negative test case" in {
    val medianFinder = new FindMedianFromStream.MedianFinder

    medianFinder.addNum(-1)
    medianFinder.addNum(-2)
    medianFinder.addNum(-3)
    medianFinder.addNum(-4)
    medianFinder.addNum(-5)
    medianFinder.findMedian() shouldBe -3.0 +- 0.0001
  }
}
