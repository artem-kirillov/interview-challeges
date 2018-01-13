import scala.annotation.tailrec

/**
  * https://leetcode.com/problems/median-of-two-sorted-arrays/
  */
object Solution {
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    if (nums1.length > nums2.length)
      findMedianSortedArraysAcc(nums2, nums1, 0, nums2.length)
    else
      findMedianSortedArraysAcc(nums1, nums2, 0, nums1.length)
  }

  @tailrec
  private def findMedianSortedArraysAcc(nums1: Array[Int], nums2: Array[Int], start: Int, end: Int): Double = {
    val x = nums1.length
    val y = nums2.length

    val partitionX = (start + end) / 2
    val partitionY = (x + y + 1) / 2 - partitionX

    val maxLeftX = if (partitionX == 0) Int.MinValue else nums1(partitionX - 1)
    val minRightX = if (partitionX == x) Int.MaxValue else nums1(partitionX)

    val maxLeftY = if (partitionY == 0) Int.MinValue else nums2(partitionY - 1)
    val minRightY = if (partitionY == y) Int.MaxValue else nums2(partitionY)

    if (maxLeftX > minRightY)
      findMedianSortedArraysAcc(nums1, nums2, start, partitionX - 1)
    else if (maxLeftY > minRightX)
      findMedianSortedArraysAcc(nums1, nums2, partitionX + 1, end)
    else if ((x + y) % 2 == 0)
      (math.max(maxLeftX, maxLeftY) + math.min(minRightX, minRightY)) / 2.0
    else
      math.max(maxLeftX, maxLeftY)
  }
}

object Main extends App {
  def assertThat[A](actual: => A, expected: => A): Unit =
    assert(actual == expected, s"$actual != $expected")

  assertThat(Solution.findMedianSortedArrays(Array(4, 5, 6), Array(1, 2)), 4)
  assertThat(Solution.findMedianSortedArrays(Array(4, 5, 6), Array(1, 2, 3)), 3.5)
  assertThat(Solution.findMedianSortedArrays(Array(4, 5, 6), Array(1, 2, 3, 3)), 3)

  assertThat(Solution.findMedianSortedArrays(Array(1, 2), Array(4, 5, 6)), 4)
  assertThat(Solution.findMedianSortedArrays(Array(1, 2, 3), Array(4, 5, 6)), 3.5)
  assertThat(Solution.findMedianSortedArrays(Array(1, 2, 3, 3), Array(4, 5, 6)), 3)
  assertThat(Solution.findMedianSortedArrays(Array(1, 2, 3, 3), Array(4, 5)), 3)

  assertThat(Solution.findMedianSortedArrays(Array(1, 2, 3, 4, 6, 10), Array(7)), 4)
  assertThat(Solution.findMedianSortedArrays(Array(1, 2, 3, 6, 7), Array(4, 5)), 4)
  assertThat(Solution.findMedianSortedArrays(Array(1, 2, 3, 6), Array(4, 5)), 3.5)

  assertThat(Solution.findMedianSortedArrays(Array(3, 4), Array.empty), 3.5)
}
