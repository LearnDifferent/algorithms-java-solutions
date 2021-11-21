import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence:
 * 最长递增子序列
 *
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the
 * order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
        // dp[3] 表示到 nums[3] 为止的 LIS(longest increasing subsequence)
        int[] dp = new int[nums.length];

        // base case: 假设每个数字都是不重复的，那么每个位置的 LIS 为 1
        Arrays.fill(dp, 1);

        // 遍历 nums 数组中的元素
        for (int i = 0; i < nums.length; i++) {
            // 遍历 nums[i] 之前，数组内的元素，看看有没有比 nums[i] 还小的元素
            for (int j = 0; j < i; j++) {
                // 如果找到了比 nums[i] 还小的元素，说明可以和 nums[i] 组成 LIS
                if (nums[i] > nums[j]) {
                    // 此时可以组成 LIS，LIS 的长度为之前元素位置的最优解 + 当前这 1 个单位
                    int current = 1 + dp[j];
                    // 因为会动态更新当前位置的最优解，所以还需要比较一下
                    dp[i] = Math.max(dp[i], current);
                }
            }
        }

        // 在 dp 最后结果中的最大值，就是 LIS
        int max = 0;
        for (int lis : dp) {
            max = Math.max(lis, max);
        }
        return max;
    }
}