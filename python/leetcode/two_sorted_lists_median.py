class Solution:
    @staticmethod
    def getMin(nums):
        return nums[0]

    @staticmethod
    def getMax(nums):
        return nums[-1]

    def partitionBy(self, lst, val, offset=0):
        if lst[0] > val:
            return offset
        if lst[-1] <= val:
            return offset + len(lst) - 1

        i = int((len(lst) - 1) / 2)
        if lst[i] <= val < lst[i + 1]:
            return offset + i
        elif lst[i] > val:
            return self.partitionBy(lst[:i], val, offset)
        else:
            return self.partitionBy(lst[i+1:], val, offset+i+1)

    def notIntersectedArrays(self, nums1, nums2, idx):
        return nums1[idx] if len(nums1) > idx else nums2[idx - len(nums1)]

    def findGlobalNthElem(self, nums1, nums2, idx):
        if len(nums1) == 0:
            return nums2[idx]
        elif len(nums2) == 0:
            return nums1[0]
        elif self.getMin(nums2) >= self.getMax(nums1):
            return self.notIntersectedArrays(nums1, nums2, idx)
        elif self.getMin(nums1) > self.getMax(nums2):
            return self.notIntersectedArrays(nums2, nums1, idx)
        # elif len(nums1) > len(nums2):
        else:
            part1_idx = int(len(nums1) / 2)
            part2_idx = self.partitionBy(nums2, nums1[part1_idx])

            left_part_len = part1_idx + part2_idx
            if left_part_len > idx:
                return self.findGlobalNthElem(nums1[:part1_idx], nums2[:part2_idx], idx)
            else:
                return self.findGlobalNthElem(nums1[part1_idx:], nums2[part2_idx:], idx - left_part_len)

    def findMedianSortedArrays(self, nums1, nums2):
        global_len = len(nums1) + len(nums2)

        if global_len % 2 == 0:
            return (self.findGlobalNthElem(nums1, nums2, int(global_len / 2) - 1) +
                    self.findGlobalNthElem(nums1, nums2, int(global_len / 2))) / 2.0
        else:
            return self.findGlobalNthElem(nums1, nums2, int(global_len / 2))


if __name__ == '__main__':
    find = Solution().findMedianSortedArrays


    def assertThat(expr, expected):
        result = expr()
        assert result == expected, '%s != %s' % (result, expected)


    assertThat(lambda: find([4, 5, 6], [1, 2]), 4)
    assertThat(lambda: find([4, 5, 6], [1, 2, 3]), 3.5)
    assertThat(lambda: find([4, 5, 6], [1, 2, 3, 3]), 3)

    assertThat(lambda: find([1, 2], [4, 5, 6]), 4)
    assertThat(lambda: find([1, 2, 3], [4, 5, 6]), 3.5)
    assertThat(lambda: find([1, 2, 3, 3], [4, 5, 6]), 3)
    assertThat(lambda: find([1, 2, 3, 3], [4, 5]), 3)

    assertThat(lambda: find([1, 2, 3, 4, 6, 10], [7]), 4)
    assertThat(lambda: find([1, 2, 3, 6, 7], [4, 5]), 4)
    assertThat(lambda: find([1, 2, 3, 6], [4, 5]), 3.5)
