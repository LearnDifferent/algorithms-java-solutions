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
        // dp[3] = 2 表示以 nums[3] 数字为结尾的 最长递增子序列 的长度为 2
        int[] dp = new int[nums.length];

        // base case：假设每个数字都是不重复的，也就是以每个数字结尾，长度都为 1
        Arrays.fill(dp, 1);

        // 遍历 nums 数组中的元素
        for (int i = 0; i < nums.length; i++) {
            // 遍历当前元素，之前的几个元素
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 如果当前元素，比当前元素之前的某个元素大，
                    // 说明当前第 i 个元素，可以从第 j 个元素组成递增子序列，
                    // 此时，再和当前位置的最优解相比较，最长的那个就是当前新的最优解
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 最后的结果，就是 dp 中的最大值
        int max = 0;
        for (int i : dp) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
}
