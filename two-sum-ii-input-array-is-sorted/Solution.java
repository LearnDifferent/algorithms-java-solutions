/**
 * 167. Two Sum II - Input array is sorted:
 * 两数之和 II - 输入有序数组
 *
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= first < second <= numbers.length.
 *
 * Return the indices of the two numbers, index1 and index2, as an integer array [index1, index2] of length 2.
 *
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 *
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 */
class Solution {

    public int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                // 题目要求是从 1 开始的 index，所以要加一
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                // 如果比预期的大，就右边的往前走
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}