/**
  * https://leetcode.com/problems/find-median-from-data-stream
  */
object FindMedianFromStream {
  class MedianFinder {
    private val leftList = scala.collection.mutable.PriorityQueue.empty[Int]
    private val rightList = scala.collection.mutable.PriorityQueue.empty[Int](implicitly[Ordering[Int]].reverse)

    def addNum(num: Int) {
      if (leftList.isEmpty || leftList.head > num) leftList += num
      else rightList += num

      val sizeDiff = leftList.size - rightList.size
      if (sizeDiff > 1) rightList += leftList.dequeue()
      else if (sizeDiff < -1) leftList += rightList.dequeue()
    }

    def findMedian(): Double = {
      val sizeDiff = leftList.size - rightList.size
      if (sizeDiff > 0) leftList.head
      else if (sizeDiff < 0) rightList.head
      else (leftList.head + rightList.head) / 2.0
    }
  }
}
