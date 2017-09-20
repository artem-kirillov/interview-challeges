package com.leetcode

/**
  * https://leetcode.com/problems/sliding-window-maximum
  */
object SlidingWindowMaximum {

  object Solution {
    import java.util

    def maxSlidingWindow(nums: Array[Int], k: Int): Array[Int] =
      if (nums.isEmpty) Array.empty
      else {
        val windowMax = new Array[Int](nums.length - k + 1)
        val slidingMax = new MaxQueue()

        var i = 0
        while (i < k - 1) {
          slidingMax.enqueue(nums(i))
          i += 1
        }
        while (i < nums.length) {
          slidingMax.enqueue(nums(i))
          windowMax(i - k + 1) = slidingMax.max()
          slidingMax.dequeue()
          i += 1
        }

        windowMax
      }

    private class MaxQueue {
      private val queue = new util.ArrayDeque[Array[Int]]()

      def enqueue(num: Int): Unit = {
        var count = 0
        while (!queue.isEmpty && queue.peekLast()(0) < num) {
          count += queue.pollLast()(1) + 1
        }
        queue.offer(Array(num, count))
      }

      def dequeue(): Unit = {
        val head = queue.peek()
        if (head(1) > 0)
          head(1) -= 1
        else
          queue.poll()
      }

      def max(): Int =
        queue.peek()(0)
    }
  }
}
