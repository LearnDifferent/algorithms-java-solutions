/**
 * 34. Find First and Last Position of Element in Sorted Array:
 *
 * 在排序数组中查找元素的第一个和最后一个位置
 *
 * 解决方案：寻找左侧边界和右侧边界的二分搜索
 *
 * Given an array of integers nums sorted in non-decreasing order,
 * find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 */
class Solution {

    public int[] searchRange(int[] nums, int target) {
        // 左侧边界的 index
        int leftIndex = getLeftIndex(nums, target);

        if (leftIndex == -1) {
            // 如果找不到，说明数组中没有 target，直接返回即可
            return new int[]{-1, -1};
        }

        // 右侧边界的 index
        int rightIndex = getRightIndex(nums, target);
        return new int[]{leftIndex, rightIndex};
    }

    private int getLeftIndex(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length;

        // 在 [left, right) 中循环，退出循环的条件是 left == right
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                // 在左侧寻找 [left, mid)
                right = mid;
            } else if (nums[mid] < target) {
                // 在右侧寻找 [mid + 1, right)
                left = mid + 1;
            } else {
                // 找到的时候，收缩左侧，以找到最左边的那个 index
                right = mid;
            }
        }

        // 我们其实需要的是 mid，不过，在最后一次循环的时候，
        // right 就等于了 mid，所以这里就用 right 作为结果即可
        // （下面的代码，写 left 和 right 一样，因为退出循环的时候 left 等于 right）
        if (right == nums.length) {
            // 因为是在 [0, nums.length) 之间查询，所以不可能小于 0；
            // 不过指针有可能刚好停到 nums.length 的位置，
            // 所以做 nums.length 的边界判断
            return -1;
        }
        // 检查一下 target 是否存在于数组中，然后再返回
        return nums[right] == target ? right : -1;
    }

    private int getRightIndex(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                // [mid + 1, right)
                left = mid + 1;
            } else if (nums[mid] > target) {
                // [left, mid)
                right = mid;
            } else {
                // 继续收缩右侧：[mid + 1, right)
                left = mid + 1;
            }
        }

        // 退出循环的时候，left == right，所以下面的代码写 left 和 right 都一样
        // 因为最后一次循环的时候，left = mid + 1，所以这里需要的 mid 实际上是 mid = left - 1
        int mid = left - 1;
        if (mid < 0) {
            // 因为指针是在 [0, nums.length) 中查找，而 mid = left - 1
            // 如果最后的指针刚好停留在 0 的位置，那么 mid 就会为 -1，也就不在数组中了。
            // 而停留在 nums.length 的时候，mid 就是 nums.length - 1，刚好在数组内。
            // 所以，mid 有可能会小于 0，但是不会超出 nums.length
            return -1;
        }
        // 返回结果之前，判断一下 target 是否是数组中的一个数值
        return nums[mid] == target ? mid : -1;
    }
}
