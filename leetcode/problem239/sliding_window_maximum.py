# https://leetcode.com/problems/sliding-window-maximum
class Solution(object):
    def maxSlidingWindow(self, nums, k):
        """
        :type nums: List[int]
        :type k: int
        :rtype: List[int]
        """

        if not nums:
            return []

        res = []
        deque = MaxQueue()
        k = min(len(nums), k)
        assert k >= 1

        for i in range(0, k - 1):
            deque.enqueue(nums[i])
        for i in range(k - 1, len(nums)):
            deque.enqueue(nums[i])
            res.append(deque.max())
            deque.dequeue()
        return res


class MaxQueue(object):
    def __init__(self):
        from collections import deque
        self.deque = deque()

    def enqueue(self, num):
        cnt = 0
        while len(self.deque) > 0 and self.deque[-1].left < num:
            cnt += self.deque.pop().right + 1
        self.deque.append(Pair(num, cnt))

    def dequeue(self):
        if self.deque[0].right > 0:
            self.deque[0].right -= 1
        else:
            self.deque.popleft()

    def max(self):
        return self.deque[0].left


class Pair(object):
    def __init__(self, left, right):
        self.left = left
        self.right = right
