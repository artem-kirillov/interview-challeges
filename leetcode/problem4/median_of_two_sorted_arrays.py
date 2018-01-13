# https://leetcode.com/problems/median-of-two-sorted-arrays/


class ListView(object):
    def __init__(self, lst, offset=0, length=None):
        self.lst = lst
        self.offset = offset
        self.length = length or len(lst)

    def __len__(self):
        return self.length

    def __getitem__(self, key):
        assert 0 <= key < self.length
        return self.lst[key + self.offset]


class Solution1:
    @staticmethod
    def getMin(nums):
        return nums[0]

    @staticmethod
    def getMax(nums):
        return nums[len(nums) - 1]

    @staticmethod
    def partitionBy(lst, val):
        list_len = len(lst)
        if lst[0] > val:
            return 0
        if lst[len(lst) - 1] <= val:
            return list_len - 1

        cur_split_idx = int((list_len - 1) / 2)
        start_idx = 0
        end_idx = list_len - 1
        while end_idx - start_idx > 0:
            if lst[start_idx] > val:
                return start_idx - 1
            if lst[end_idx] <= val:
                return end_idx - 1
            elif lst[cur_split_idx] > val:
                end_idx -= cur_split_idx
            else:
                start_idx += cur_split_idx

            cur_split_idx = int((end_idx - start_idx) / 2)

    def notIntersectedArrays(self, nums1, nums2, idx):
        return nums1[idx] if len(nums1) > idx else nums2[idx - len(nums1)]

    def findGlobalNthElem(self, nums1, nums2, idx):
        if len(nums1) == 0:
            return nums2[idx]
        elif len(nums2) == 0:
            return nums1[idx]

        min1 = self.getMin(nums1)
        max1 = self.getMax(nums1)
        min2 = self.getMin(nums2)
        max2 = self.getMax(nums2)
        if min2 >= max1:
            return self.notIntersectedArrays(nums1, nums2, idx)
        elif min1 > max2:
            return self.notIntersectedArrays(nums2, nums1, idx)
        else:
            part1_idx = int(len(nums1) / 2)
            part2_idx = self.partitionBy(nums2, nums1[part1_idx])

            left_part_len = part1_idx + part2_idx
            if left_part_len > idx:
                return self.findGlobalNthElem(
                    ListView(nums1.lst, nums1.offset, part1_idx),
                    ListView(nums2.lst, nums2.offset, part2_idx), idx)
            else:
                return self.findGlobalNthElem(
                    ListView(nums1.lst, part1_idx, nums1.length - part1_idx),
                    ListView(nums2.lst, part2_idx, nums2.length - part2_idx), idx - left_part_len)

    def findMedianSortedArrays(self, nums1, nums2):
        global_len = len(nums1) + len(nums2)

        if global_len % 2 == 0:
            return (self.findGlobalNthElem(ListView(nums1), ListView(nums2), int(global_len / 2) - 1) +
                    self.findGlobalNthElem(ListView(nums1), ListView(nums2), int(global_len / 2))) / 2.0
        else:
            return self.findGlobalNthElem(ListView(nums1), ListView(nums2), int(global_len / 2))


if __name__ == '__main__':
    find = Solution1().findMedianSortedArrays


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

    assertThat(lambda: find([3, 4], []), 3.5)
